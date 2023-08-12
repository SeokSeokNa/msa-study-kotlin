FROM openjdk:21-ea-17-slim
VOLUME /tmp
COPY build/libs/user-service-1.0.jar UserService.jar
ENTRYPOINT ["java","-jar","UserService.jar"]
# ./gradlew bootJar
# docker build -t wonseok/user-service:1.0 .
# -t 는 --tag의 약자 이미지명/태그 도커파일위치
# docker run -d --network ecommerce-network --name user-service wonseok/user-service:1.0
#