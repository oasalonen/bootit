
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
## Kubernetes
- API versions to stable where possible
- Review/cleanup of yaml files in general
- Check if elk namespace should not be applied with the rest
- Replicas, simulate failures
- CD pipeline with Kubernetes rollouts (gitops, flux)
- Readiness check on actuator with multiple replicas for rollous
- monitor postgresql with elk, alert on certain queries
- simplify port mapping: all endpoints for general use to 80
- check out https://github.com/fabric8io/configmapcontroller for redeploying after configmap updates
## App
- correlation ID (can it be sent to db queries as well?)
- authorization, oauth2 providers  
- caching (with redis)  
- check if mq code can be mostly removed (besides listener)  
- rest calls to elastic? (RestTemplate)  
- javax.validation of params  
- faster unit test (less spring init)  
  - https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html
- check spring cloud and kubernetes integration