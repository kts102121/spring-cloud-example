# Spring Cloud Exercise

## Introduction
Stay awhile and listen! (:p)

This is a small toy project inspired by
[Spring Microservices in Action](https://www.manning.com/books/spring-microservices-in-action).

I am trying to change:
1. eureka -> consul
2. [hystrix](https://github.com/Netflix/Hystrix) -> [resilience4j](https://github.com/resilience4j/resilience4j)
3. [docker-maven-plugin by spotify](https://github.com/spotify/docker-maven-plugin) -> [dockerfile-maven-plugin](https://github.com/spotify/dockerfile-maven)
4. docker-compose -> kubernetes

## How to run
```bash
mkdir -p /tmp/consul
git clone https://github.com/kts102121/spring-cloud.git
cd spring-cloud
mvn clean package dockerfile:build
docker-compose -f docker/default/docker-compose.yml up
```
Successful build will run consul, configserver and service containers

## How to test

#### Test if config server fetched *.yml according to profile
To see if the configuration variables are correctly injected for **userservice/userservice.yml** from config servers
```bash
curl localhost:8888/userservice/default
```

#### Test if service containers are running
```bash
curl localhost:8085/v1/user/1
```

#### To call the API through API Gateway
```bash
curl localhost:8080/userservice/v1/user/1/inventory
```
This will call <code>/v1/user</code> with Path parameter <code>1</code> (user id).
It will also check inventories added by this user and add them

#### How to test fallback
```bash
docker stop [inventory service container id]
curl localhost:8080/userservice/v1/user/1/inventory
```
This should give you 
```json
{"userId":1,"userName":"Ron","userEmail":"ron@bar.com","inventories":[{"inventoryId":0,"inventoryName":"Something is wrong. Please try again later"}]}
```

Try restart inventory service and curl again
```json
{"userId":1,"userName":"Ron","userEmail":"ron@bar.com","inventories":[{"inventoryId":1,"inventoryName":"Oreo"}]}
```

#### To docker-compose with different profiles
```bash
docker-compose -f docker/dev/docker-compose.yml up
```