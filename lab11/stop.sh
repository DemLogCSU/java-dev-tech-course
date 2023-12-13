#!/bin/bash

if [ $# -eq 0 ]
then
  docker-compose down --rmi local --remove-orphans
else
  docker-compose rm -fsv $@
fi
