# Spring Cloud Exercise

```bash
git clone https://github.com/kts102121/spring-cloud.git
cd spring-cloud
mvn clean package dockerfile:build
```

Successful build will run consul, configserver and userservice containers

```bash
curl localhost:8888/userservice/default
```
To see if the configuration variables are correctly injected for userservice

```bash
curl localhost:8080/v1/user/1
```
To see if userservice is running
