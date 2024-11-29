# build with: docker build -t lenartl/petstore-backend .
# push with: docker push lenartl/petstore-backend
FROM maven:3.9.6-amazoncorretto-17-al2023 AS build
WORKDIR /opt/build
COPY ./pom.xml .
RUN mvn dependency:go-offline
COPY ./src ./src
RUN mvn clean install -Dmaven.test.skip=true

FROM amazoncorretto:17.0.10-al2023-headless

COPY --from=build /opt/build/target/*.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

EXPOSE 8080