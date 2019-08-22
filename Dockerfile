FROM maven:3.5.2-jdk-8-alpine AS MAVEN_TOOL_CHAIN
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:8-jdk-alpine


ARG JAR_FILE=/target/websocket-demo.jar
COPY --from=MAVEN_TOOL_CHAIN /tmp/target/websocket-demo*.jar websocket-demo.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/websocket-demo.jar"]
