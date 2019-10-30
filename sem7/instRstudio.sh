#! /bin/sh
sudo apt-get install libopenblas-base r-base \
&& sudo apt-get install --upgrade gdebi  \
&& wget https://download1.rstudio.org/rstudio-xenial-1.1.419-amd64.deb  \
&& sudo gdebi rstudio-xenial-1.1.419-amd64.deb  \
&& R
