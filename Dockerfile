# Etapa de build
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY . .
RUN mvn clean package -DskipTests

# Etapa de runtime
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

# Copiar collections e keys para a imagem
COPY collections /app/collections
EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
