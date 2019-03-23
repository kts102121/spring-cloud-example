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

##### Create .env
```text
GIT_URL={your config github repository}
GIT_CREDENTIALS_USERNAME={user username}
GIT_CREDENTIALS_PASSWORD={cipher}{config server encrypted keys}
ENCRYPT_KEY={your key to encrypt and decrypt sensitive information. e.g. avery$ecretkey} 
CONSUL_URL=http://consulserver
CONSUL_PORT=8500
CONFIG_URL=http://configserver:8888
CONFIG_PORT=8888
DB_USER={DB username}
DB_PASSWORD={DB password}
ENCRYPTED_DB_PASS={cipher}{encrypted db password}
DB_NAME=kydy
DB_PORT=5432
KAFKA_URL=kafkaserver
KAFKA_PORT=9092
JWT_SIGNING_KEY={jwt signing key. e.g avery$secretjwtkey}
```
Please create this file under your project root

##### Create config repo
please make config files for auth, user, and inventory services. For example:
```yml
spring.jpa.database: "POSTGRESQL"
spring.datasource.platform:  "postgres"
spring.jpa.show-sql: "true"
spring.database.driverClassName: "org.postgresql.Driver"
spring.datasource.url: "jdbc:postgresql://database:5432/example"
spring.datasource.username: "root"
spring.datasource.password: ${ENCRYPTED_DB_PASS}
spring.datasource.testWhileIdle: "true"
spring.datasource.validationQuery: "SELECT 1"
spring.jpa.properties.hibernate.dialect: "org.hibernate.dialect.PostgreSQLDialect"
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation: "true"
spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults: "false"
spring.jpa.hibernate.ddl-auto: "create-drop"
signing.key: ${JWT_SIGNING_KEY}
```
Place the above file under authservice, userservice and inventoryservice in that repo
(create different folders each)

```bash
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

#### Test user signup
```bash
curl --header "Content-Type: application/json" -X POST --data '{"username":"kts102111","contacts":{"email":"foo@bar.com","countryCode":"82","phoneNumber":"01000000000"},"credentials":{"password":"test","roles":[{"role": "ROLE_USER"}]}}' http://localhost:8080/userservice/v1/user
```

Successful signup returns 200

#### Test login
```bash
curl -vv -X POST -H 'Expect:' -u eagleeye:thisissecret -F 'username=kts102111' -F 'password=test' -F 'scope=webclient' -F 'grant_type=password' localhost:8080/authservice/oauth/token
```

successful login returns
```json
{
    "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NTE3NDE3NjYsInVzZXJfbmFtZSI6Imt0czEwMjEiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiMmI3MGJlNjItMmY3Ni00NTNhLTk0NWUtZDFjNTJhZTBiMWZkIiwiY2xpZW50X2lkIjoiZWFnbGVleWUiLCJzY29wZSI6WyJ3ZWJjbGllbnQiXX0.T86KWCcyjcWcxJy3fj0EBcsXx6256dvly8azARUqkw0",
    "token_type": "bearer",
    "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJrdHMxMDIxIiwic2NvcGUiOlsid2ViY2xpZW50Il0sImF0aSI6IjJiNzBiZTYyLTJmNzYtNDUzYS05NDVlLWQxYzUyYWUwYjFmZCIsImV4cCI6MTU1NDI5MDU2NiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImp0aSI6ImQzY2ZkMTNiLWY1NmMtNDU1YS05ZmZhLWQ5MzgzOWRkNTdiNiIsImNsaWVudF9pZCI6ImVhZ2xlZXllIn0.KjB40OiPF-s04mYfgT5UmxTQwOUsbPMobzEd8TjcFI0",
    "expires_in": 43199,
    "scope": "webclient",
    "jti": "2b70be62-2f76-453a-945e-d1c52ae0b1fd"
}
```

#### To docker-compose with different profiles
```bash
docker-compose -f docker/dev/docker-compose.yml up
```
