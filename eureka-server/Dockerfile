FROM openjdk:8-alpine
VOLUME /tmp
EXPOSE 8090
ADD ./target/eureka-server.jar  eureka-server.jar
ADD ./config/docker config
ENTRYPOINT [ "java","-jar","eureka-server.jar" ]