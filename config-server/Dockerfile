FROM openjdk:8-alpine
VOLUME /tmp
EXPOSE 8888
ADD ./target/config-server.jar  config-server.jar
ADD ./config/docker config
ENTRYPOINT [ "java","-jar","config-server.jar" ]