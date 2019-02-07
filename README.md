# Spring Cloud Exercise


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

#### To docker-compose with different profiles
```bash
docker-compose -f docker/dev/docker-compose.yml up
```