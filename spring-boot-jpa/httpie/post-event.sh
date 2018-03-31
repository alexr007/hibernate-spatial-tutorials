#! /bin/bash

if [[ -z "$1" ]]; then
 echo "Usage: $0 <json.file>"
 exit 1
fi

JSONFILE=$1

http POST localhost:8080/events < ${JSONFILE}