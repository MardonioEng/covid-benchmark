FROM maven:3.9-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

#ENV SPRING_PROFILES_ACTIVE=prod
ENV DB_HOST=${DB_HOST}
ENV DB_USERNAME=${DB_USERNAME}
ENV DB_PASSWORD=${DB_PASSWORD}
ENV BASE_URL=${BASE_URL}
ENV API_KEY=${API_KEY}

EXPOSE ${SERVER_PORT}

ENTRYPOINT ["java", "-jar", "app.jar"]