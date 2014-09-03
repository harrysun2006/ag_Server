#!/bin/sh
date=`date +%Y%m%d`
file="viewbar$date.tgz"
find ROOT -newer README.TXT -type f \
  -not -iwholename "./lib/*" -not -iname "vssver*.scc" \
  -print | xargs tar czf $file
