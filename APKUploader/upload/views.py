import os
from wsgiref.util import FileWrapper

from django.core.files.storage import FileSystemStorage
from django.http import HttpResponse
from django.shortcuts import render, redirect

# Create your views here.
from upload.forms import DocumentForm
from upload.forms import ResultForm
from upload.models import Document
from django.views.static import serve


def model_form_upload(request):
    last_five = Document.objects.all().order_by('-id')[:5]
    for one in last_five:
        filepath = os.path.join('resultfiles', one.md5sum + '.json')
        if os.path.exists(filepath):
            one.status = "Ready"
        else:
            one.status = "NotReady"
    if request.method == 'POST':
        form = DocumentForm(request.POST, request.FILES)
        if form.is_valid():
            model_instance = form.save()
            try:
                apk = Document.objects.get(md5sum=model_instance.md5sum)
            except Document.DoesNotExist:
                model_instance.save()
                apk = model_instance
            return render(request, 'index.html', {
                'unique_id' : apk.unique_id,
                'last_five': last_five
            })
        else:
            form = DocumentForm()
            error = "Please upload valid apk file"
            return render(request, 'index.html', {
                'form': form,
                'unique_id': '',
                'error': error,
                'last_five': last_five
            })
    else:
        form = DocumentForm()
        return render(request, 'index.html', {
            'form': form,
            'unique_id': '',
            'last_five': last_five
        })


def check_result(request,unique_id):
    last_five = Document.objects.all().order_by('-id')[:5]
    for one in last_five:
        filepath = os.path.join('resultfiles', one.md5sum + '.json')
        if os.path.exists(filepath):
            one.status = "Ready"
        else:
            one.status = "NotReady"
    error = ''
    if request.POST.get("unique_id",'') != '' or unique_id != '':
        if request.POST.get("unique_id", '') != '':
            unique_id = request.POST.get("unique_id", '')
        try:
            apk = Document.objects.get(unique_id=unique_id)
            filepath = os.path.join('resultfiles', apk.md5sum + '.json')
            if os.path.exists(filepath):
                wrapper = FileWrapper(open(filepath, 'rb'))
                response = HttpResponse(wrapper, content_type='text/plain')
                response['Content-Disposition'] = 'attachment; filename=%s.txt' % apk.unique_id
                response['Content-Length'] = os.path.getsize(filepath)
                return response
            else:
                error = "Result not ready yet!"
        except Document.DoesNotExist:
            error = "Unique ID not Found!"
    form = ResultForm()
    return render(request, 'result.html', {
        'form': form,
        'error': error,
        'last_five': last_five
    })
