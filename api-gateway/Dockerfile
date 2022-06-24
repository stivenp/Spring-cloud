FROM openjdk:8-alpine
VOLUME /tmp
EXPOSE 8092
ADD ./target/api-gateway.jar  api-gateway.jar
ADD ./config/docker config
ENTRYPOINT [ "java","-jar","api-gateway.jar" ]