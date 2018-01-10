from os import listdir
from os.path import isfile, join
import json
import os


def finalize():
	path = '/mnt/41572241-f10a-40a5-8de6-adfc18763041/ransomware/APKUploader/rawRes/'
	output = '/mnt/41572241-f10a-40a5-8de6-adfc18763041/ransomware/APKUploader/resultfiles/'
	onlyfiles = [f for f in listdir(path) if isfile(join(path, f))]
	
	for f in onlyfiles:
		results = {}
		ID = None
		with open(path+f) as data_file:
			content = data_file.readlines() 
			idx=0
			for line in content:
       				if( len(line) < 10 ):
					continue
				if ID == None:
					s = line.find('IDXP:')
					e = line.find('.apk')
					ID = line[s+5:e]
			        start = line.find('payload:')
       				j = line[start+8:-3]
        			d=json.loads(j)
                        	if d['PackageName']=='eu.chainfire.supersu' : continue
				results[idx]={'package': d['PackageName'], 'class': d['ClassName'], 'method': d['MethodName'], 'timestamp': d['Timestamp'],'params': d['Return']}
				idx = idx + 1
            
		# print ID
		# results = sorted(results,key=lambda x: x[3])
		with open(output+ID+'.json', 'w') as outfile:
			json.dump(results, outfile, indent=2)
		os.remove(path+f)

finalize()

path = '/mnt/41572241-f10a-40a5-8de6-adfc18763041/ransomware/APKUploader/rawRes/'
output = '/mnt/41572241-f10a-40a5-8de6-adfc18763041/ransomware/APKUploader/resultfiles/'
onlyfiles = [f for f in listdir(output) if isfile(join(output, f))]
for f in onlyfiles:
	j = json.loads(open(output+f).read())
	print j["0"]

