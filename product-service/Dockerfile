FROM openjdk:8-alpine
VOLUME /tmp
EXPOSE 8020
ADD ./target/product-service.jar  product-service.jar
ADD ./config/docker config
ENTRYPOINT [ "java","-jar","product-service.jar" ]