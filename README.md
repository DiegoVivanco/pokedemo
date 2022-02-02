# Product Backend

Java 11 project with Spring Boot, this project contains: 
   - Rest template
   - Redis Cache
   - Unit Test
   - Integration Test
   - Swagger Documentation


## Swagger Documentation
When you run the app you can access to Swagger documentation in the next path: /swagger-ui.html.

## Build Docker Image
```bash
# Build the image
docker build -t pokedemo.jar .
```

## Check Docker Image
```bash
# Check the image
docker image ls
```

## Run Docker Image
```bash
# Check the image
docker run -p 9090:8080 pokedemo.jar
```
In the run command, I have specified that the port 8080 on the container should be mapped to the port 9090 on the Host OS.


##Run redis-server
docker run --name redis-t -d -p 6379:6379 redis:6.0

## Access URL:

- Swagger UI -> http://localhost:8080/swagger-ui.html
- Spring Boot actuator endpoints:

    http://localhost:8080/actuator/health
    
    http://localhost:8080/actuator/info
  
- The 5 heaviest Pokémons.
  http://localhost:8080/api/v1/pokedemo?sort=height&sortOrder=desc&size=5
  
- The 5 highest Pokémons.
  http://localhost:8080/api/v1/pokedemo?sort=weight&sortOrder=desc&size=5
  
- The 5 Pokémons with more base experience.
  http://localhost:8080/api/v1/pokedemo?sort=baseExperience&sortOrder=desc&size=5