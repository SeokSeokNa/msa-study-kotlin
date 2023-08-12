FROM openjdk:21-ea-17-slim
VOLUME /tmp
COPY build/libs/discovery-service-1.0.jar DiscoveryService.jar
ENTRYPOINT ["java","-jar","DiscoveryService.jar"]
# ./gradlew bootJar
# docker build -t wonseok/discovery-service:1.0 .
# -t 는 --tag의 약자 이미지명/태그 도커파일위치
# docker run -d -p 8761:8761 --network ecommerce-network --name discovery-service wonseok/discovery-service:1.0
#