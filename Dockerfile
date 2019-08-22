FROM maven:3.5.2-jdk-8-alpine AS MAVEN_TOOL_CHAIN
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn package

FROM tomcat:9.0-jre8-alpine

RUN value=`cat conf/server.xml` && echo "${value//8080/80}" >| conf/server.xml

COPY --from=MAVEN_TOOL_CHAIN /tmp/target/wizard*.war $CATALINA_HOME/webapps/wizard.war

HEALTHCHECK --interval=1m --timeout=3s CMD wget --quiet --tries=1 --spider http://localhost/wizard/ || exit 1