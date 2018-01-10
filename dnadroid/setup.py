#!/usr/bin/env python

from setuptools import setup, find_packages
from dnadroid import release

# List of project's dependencies
install_requires = [
    'dnadroid_common'
]

#+---------------------------------------------------------------------------+
# Definition of the project
#+---------------------------------------------------------------------------+
setup(
    name = release.name,
    packages = find_packages(),
    scripts = ["dnadroid.py"],
    
    install_requires=install_requires
)
    
    

