#!/bin/bash
set -e

gradle -p ./backend assemble

docker build -t psk/backend:latest -f ./build/backend/Dockerfile -o ./images .

docker save psk/backend:latest >  psk_backend_latest.tar
