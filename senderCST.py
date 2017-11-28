#!/usr/bin/python

import urllib2, base64
import ssl
import requests
import time
from base64 import b64encode
from requests.auth import HTTPDigestAuth
import datetime as date
import hashlib
import re
from datetime import datetime

set_ip_server = "10.10.56.133"

def createhash(content):
    sha256 = hashlib.sha256()
    sha256.update(content)
    return sha256.hexdigest()


def send_post(content):
	response = requests.post('https://'+set_ip_server+'/insertFiscalDocument.php',data=content,auth=HTTPBasicAuth('user', 'pass'),headers={"Content-Type": "application/x-www-form-urlencoded" }, verify=False)	
	print response.text
	assert response.status_code == 200
	return response.text



def send_get(content):
	response = requests.get('https://'+set_ip_server+'/getDailyStatus.php',data=content,auth=HTTPBasicAuth('user', 'pass'),headers={"Content-Type": "application/x-www-form-urlencoded" }, verify=False)	
	print response.text
	assert response.status_code == 200
	return response.text






exit(0)
ctx = ssl.create_default_context()
ctx.check_hostname = False
ctx.verify_mode = ssl.CERT_NONE



request = urllib2.Request('https://'+set_ip_server+'/insertFiscalDocument.php')
base64string = base64.encodestring('%s:%s' % ('userCASSA', 'PASS')).replace('\n', '')
request.add_header("Authorization", "Basic %s" % base64string)   

print request
response = urllib2.urlopen(request, context=ssl._create_unverified_context())

print response
exit(0)
