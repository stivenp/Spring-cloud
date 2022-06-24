FROM openjdk:8-alpine
VOLUME /tmp
ADD ./target/user-service.jar  user-service.jar
ADD ./config/docker config
ENTRYPOINT [ "java","-jar","user-service.jar" ]