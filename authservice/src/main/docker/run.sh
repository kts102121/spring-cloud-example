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
echo "Waiting for the database server to start on port $DB_PORT"
echo "********************************************************"
while ! `nc -z database $DB_PORT`; do sleep 3; done
echo "******** Database Server has started "

echo "********************************************************"
echo "Waiting for the configuration server to start on port $CONFIG_PORT"
echo "********************************************************"
while ! `nc -z configserver $CONFIG_PORT`; do sleep 3; done
echo "*******  Configuration Server has started"

echo "********************************************************"
echo "Starting User Service  "
echo "********************************************************"
java -cp app:app/lib/* -Djava.security.egd=file:/dev/./urandom \
     -Dserver.port=$SERVER_PORT \
     -Dspring.cloud.config.uri=$CONFIG_URL \
     -Dspring.profiles.active=$PROFILE \
     org.ron.authservice.AuthServiceApplication