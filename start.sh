#!/bin/bash

exec java -Dserver.port=$PORT -Dspring.profiles.active=prod -jar forum.jar

