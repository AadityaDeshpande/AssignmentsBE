#! /bin/sh
sudo apt-get install software-properties-common \
&& sudo add-apt-repository ppa:deadsnakes/ppa \
&& sudo apt-get update \
&& sudo apt-get install python3.6 \
&& python3 --version
