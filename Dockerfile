FROM maven:3.5.2-jdk-8-alpine AS MAVEN_TOOL_CHAIN
COPY pom.xml /tmp/
WORKDIR /tmp/

# copy the pom file and run maven package to cache the dependencies in initial layer
RUN mvn clean package -Dmaven.test.skip=true

# copy the source to only re-package this part if src has changed
COPY src /tmp/src/
RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:8-jdk-alpine

EXPOSE 80

ARG JAR_FILE=/target/websocket-demo.jar
COPY --from=MAVEN_TOOL_CHAIN /tmp/target/websocket-demo*.jar websocket-demo.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/websocket-demo.jar"]
