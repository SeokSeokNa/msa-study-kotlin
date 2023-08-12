FROM openjdk:21-ea-17-slim
VOLUME /tmp
COPY build/libs/apigateway-service-1.0.jar ApigatewayService.jar
ENTRYPOINT ["java","-jar","ApigatewayService.jar"]
# ./gradlew bootJar
# docker build -t wonseok/apigateway-service:1.0 .
# -t 는 --tag의 약자 이미지명/태그 도커파일위치
# docker run -d -p 8000:8000 --network ecommerce-network --name apigateway-service wonseok/apigateway-service:1.0
#