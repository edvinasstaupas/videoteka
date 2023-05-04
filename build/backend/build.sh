#!/bin/bash
set -e

gradle -p ../../backend assemble

docker build -t psk/backend:1.0 -f ./Dockerfile ../../
