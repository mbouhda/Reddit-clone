FROM openjdk:8-jdk-alpine

RUN apk update && \
      apk add sudo && \
      apk add bash

ARG JAR_FILE=target/reddit-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} redit-clone.jar

COPY wait-for-it.sh wait-for-it.sh
RUN sudo chmod +x wait-for-it.sh

CMD ["./wait-for-it.sh" , "mysqlHost:port" , "--strict" , "--timeout=60" , "--" , "java", "-jar", "/redit-clone.jar"]