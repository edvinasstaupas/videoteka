#!/bin/bash
set -e

gradle -p ./backend assemble

docker build -t psk/backend:latest -f ./build/backend/Dockerfile .

mkdir ./images

docker save psk/backend:latest > ./images/psk_backend_latest.tar
