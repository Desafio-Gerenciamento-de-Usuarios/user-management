# Etapa de build
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# Etapa de runtime
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copia o JAR final do build
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta 8081
EXPOSE 8081

# Executa a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
