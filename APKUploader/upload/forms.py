import hashlib

from django import forms
from upload.models import Document
from django.utils.translation import ugettext_lazy as _


class DocumentForm(forms.ModelForm):
    class Meta:
        model = Document
        fields = ('document',)
        labels = {
            'document': _(''),
        }

    def save(self):
        form = super(DocumentForm, self).save(commit=False)
        md5 = hashlib.md5()
        for chunk in form.document.chunks():
            md5.update(chunk)
        form.md5sum = md5.hexdigest()
        return form


class ResultForm(forms.Form):
    unique_id = forms.CharField(max_length=100)