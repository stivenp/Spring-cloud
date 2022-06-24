FROM openjdk:8-alpine
VOLUME /tmp
ADD ./target/product-service.jar  product-service.jar
ADD ./config/docker config
ENTRYPOINT [ "java","-jar","product-service.jar" ]