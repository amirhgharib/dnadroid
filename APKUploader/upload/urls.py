from django.conf.urls import url

from . import views

urlpatterns = [
    url(r'^$', views.model_form_upload, name='index'),
    url(r'^result$', views.check_result, {'unique_id' : ''}, name='result'),
    url(r'^result/(?P<unique_id>[-\w]+)/$', views.check_result, name='result'),

]