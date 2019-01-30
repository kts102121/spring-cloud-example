#!/bin/sh

echo "********************************************************"
echo "Waiting for the consul server to start  on port $CONSUL_PORT"
echo "********************************************************"
while ! `nc -z consulserver $CONSUL_PORT`; do sleep 3; done
echo ">>>>>>>>>>>> Consul Server has started"

echo "********************************************************"
echo "Starting Config Server  "
echo "********************************************************"
java -cp app:app/lib/* -Djava.security.egd=file:/dev/./urandom org.ron.configserver.ConfigServerApplication
