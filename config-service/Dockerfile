FROM openjdk:21-ea-17-slim
VOLUME /tmp
COPY apiEncryptionKey.jks apiEncryptionKey.jks
COPY build/libs/config-service-1.0.jar ConfigServer.jar
ENTRYPOINT ["java","-jar","ConfigServer.jar"]
# ./gradlew bootJar
# docker build -t wonseok/config-service:1.0 .
# -t 는 --tag의 약자 이미지명/태그 도커파일위치
# docker run -d -p 8888:8888 --network ecommerce-network --name config-service wonseok/config-service:1.0
#