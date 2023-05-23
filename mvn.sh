#!/bin/bash

echo "maven script running in $1 mode..."

if [ $1 == "dev" ]; then
  source ./mvn-vars-dev
  mvn -P dev spring-boot:start package -Ddatasource.url=$db_url -Ddatasource.username=$db_username -Ddatasource.password=$db_password
elif [ $1 == "dev-stop" ]; then
  mvn -P dev spring-boot:stop
elif [ $1 == "backend" ]; then
  source ./mvn-vars-dev
  mvn -P dev spring-boot:run -Ddatasource.url=$db_url -Ddatasource.username=$db_username -Ddatasource.password=$db_password
elif [ $1 == "test" ]; then
  source ./mvn-vars-dev
  mvn -P dev test -Ddatasource.url=$db_url -Ddatasource.username=$db_username -Ddatasource.password=$db_password
elif [ $1 == "release" ]; then
  source ./mvn-vars-release
  mvn -P release clean package -Ddatasource.url=$db_url -Ddatasource.username=$db_username -Ddatasource.password=$db_password
else
  echo "unknown mode"
fi
