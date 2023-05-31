#!/bin/bash
set -e



docker build -t psk/backend:1.0 -f ./Dockerfile ../../
