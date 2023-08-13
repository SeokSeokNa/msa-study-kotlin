FROM openjdk:21-ea-17-slim
VOLUME /tmp
COPY build/libs/order-service-1.0.jar OrderService.jar
ENTRYPOINT ["java","-jar","OrderService.jar"]
# ./gradlew bootJar
# docker build -t wonseok/order-service:1.0 .
# -t 는 --tag의 약자 이미지명/태그 도커파일위치
# docker run -d --network ecommerce-network --name order-service wonseok/order-service:1.0
#