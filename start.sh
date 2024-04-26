#!/bin/bash

if [[ $DYNO == "web"* ]]; then
  java -Dserver.port=$PORT -Dspring.profiles.active=prod -jar forum.jar
fi

