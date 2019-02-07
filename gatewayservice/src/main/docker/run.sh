#!/bin/sh

getPort() {
    echo $1 | cut -d : -f 3 | xargs basename
}

echo "********************************************************"
echo "Waiting for the consul server to start on port $CONSUL_PORT"
echo "********************************************************"
while ! `nc -z consulserver $CONSUL_PORT`; do sleep 3; done
echo "******* Consul Server has started"

echo "********************************************************"
echo "Starting Gateway Service  "
echo "********************************************************"
java -cp app:app/lib/* \
     -Djava.security.egd=file:/dev/./urandom \
     -Dserver.port=$SERVER_PORT \
     -Dspring.profiles.active=$PROFILE \
     org.ron.gatewayservice.GatewayServiceApplication