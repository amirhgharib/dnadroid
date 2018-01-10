# Create your models here.
import uuid
import hashlib
import os

from django.core.exceptions import ValidationError
from django.core.files.storage import FileSystemStorage
from django.db import models


class MediaFileSystemStorage(FileSystemStorage):
    def get_available_name(self, name, max_length=None):
        if max_length and len(name) > max_length:
            raise(Exception("name's length is greater than max_length"))
        return name

    def _save(self, name, content):
        if self.exists(name):
            # if the file exists, do not call the superclasses _save method
            return name
        # if the file is new, DO call it
        return super(MediaFileSystemStorage, self)._save(name, content)


def media_file_name(instance, filename):
    h = instance.md5sum
    basename, ext = os.path.splitext(filename)
    return os.path.join('mediafiles', h + ext.lower())


def validate_file_extension(value):
    if not value.name.endswith('.apk'):
        raise ValidationError(u'Error message')


class Document(models.Model):
    unique_id = models.CharField(max_length=255, unique=True, default=uuid.uuid4)
    document = models.FileField(upload_to=media_file_name, storage=MediaFileSystemStorage(), validators=[validate_file_extension])
    md5sum = models.CharField(max_length=36,unique=True)
    uploaded_at = models.DateTimeField(auto_now_add=True)
    status = models.CharField(max_length=36)

    def save(self, *args, **kwargs):
        if not self.pk:  # file is new
            md5 = hashlib.md5()
            for chunk in self.document.chunks():
                md5.update(chunk)
            self.md5sum = md5.hexdigest()
        super(Document, self).save(*args, **kwargs)