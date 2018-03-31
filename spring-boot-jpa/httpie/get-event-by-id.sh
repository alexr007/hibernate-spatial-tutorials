#! /bin/bash

if [[ -z "$1" ]]; then
 echo "Usage: $0 <event Id>"
 exit 1
fi

ID=$1

http GET localhost:8080/events/${ID}