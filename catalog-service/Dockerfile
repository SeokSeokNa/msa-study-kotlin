FROM openjdk:21-ea-17-slim
VOLUME /tmp
COPY build/libs/catalog-service-1.0.jar CatalogService.jar
ENTRYPOINT ["java","-jar","CatalogService.jar"]
# ./gradlew bootJar
# docker build -t wonseok/catalog-service:1.0 .
# -t 는 --tag의 약자 이미지명/태그 도커파일위치
# docker run -d --network ecommerce-network --name catalog-service wonseok/catalog-service:1.0
#