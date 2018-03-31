#! /bin/bash

if [[ -z "$1" ]] || [[ -z "$2" ]]; then
 echo "Usage: $0 <lon> <lat>"
 exit 1
fi

LON=$1
LAT=$2

http localhost:8080/events/distance/${LON}/${LAT}