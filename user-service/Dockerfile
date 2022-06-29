FROM openjdk:8-alpine
VOLUME /tmp
EXPOSE 8010
ADD ./target/user-service.jar  user-service.jar
ADD ./config/docker config
ENTRYPOINT [ "java","-jar","user-service.jar" ]