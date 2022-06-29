FROM openjdk:8-alpine
VOLUME /tmp
EXPOSE 8030
ADD ./target/item-service.jar  item-service.jar
ADD ./config/docker config
ENTRYPOINT [ "java","-jar","item-service.jar" ]