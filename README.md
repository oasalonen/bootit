
# bootit  
Playground for Java Spring Boot  
  
# Run in Kubernetes  
## Build and push app to a private container registry  
You need to add the private registry credentials to Docker
```
docker login <registry-url> <user> <pass>
```
Then push the image to it
```  
$ ./gradlew dockerPush  
```  

## Fire up the cluster
Follow the setup instructions for [bootit cluster](https://github.com/oasalonen/local-cluster). Then hit some endpoints, e.g. ```http://localhost:8002/api```

# Next (notes for myself)
## Kubernetes
- API versions to stable where possible
- Review/cleanup of yaml files in general
- Check if elk namespace should not be applied with the rest
- monitor postgresql with elk, alert on certain queries
- simplify port mapping: all endpoints for general use to 80
- check out https://github.com/fabric8io/configmapcontroller for redeploying after configmap updates
## App
- correlation ID (can it be sent to db queries as well?)
- authorization, oauth2 providers  
- check if mq code can be mostly removed (besides listener)  
- rest calls to elastic? (RestTemplate)  
- javax.validation of params  
- faster unit test (less spring init)  
  - https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html
- check spring cloud and kubernetes integration
- circuit breaker with hystrix