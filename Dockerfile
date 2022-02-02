FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} pokedemo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/pokedemo.jar"]