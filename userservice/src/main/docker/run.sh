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
echo "Waiting for the configuration server to start on port $CONFIG_PORT"
echo "********************************************************"
while ! `nc -z configserver $CONFIG_PORT`; do sleep 3; done
echo "*******  Configuration Server has started"

echo "********************************************************"
echo "Starting Organization Service  "
echo "********************************************************"
java -cp app:app/lib/* -Djava.security.egd=file:/dev/./urandom -Dserver.port=$SERVER_PORT -Dspring.cloud.config.uri=$CONFIG_URL org.ron.userservice.UserServiceApplication