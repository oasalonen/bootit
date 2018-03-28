
# bootit  
Playground for Java Spring Boot  
  
# Run in Kubernetes  
## Create private local docker registry  
You only need to run this command once to create the registry. The registry will autostart every time docker starts.
```  
$ docker-compose -f kubernetes/docker-registry/docker-compose.yml up -d  
```  
  
## Build and push app to local registry  
```  
$ ./gradlew dockerPush  
```  
  
## (Optional) Fire up ELK stack
```
$ kubectl apply -f kubernetes/elk
```
You can now browse to ```http://localhost:5601/``` to look  at Kibana.

## Fire up the app
```
$ kubectl apply -f kubernetes/app
```
Now hit some endpoints, e.g. ```http://localhost:8002/api```

# Next (notes for myself)  
- authorization, oauth2 providers  
- caching (with redis)  
- check if mq code can be mostly removed (besides listener)  
- rest calls to elastic? (RestTemplate)  
- javax.validation of params  
- faster unit test (less spring init)  
  - https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html