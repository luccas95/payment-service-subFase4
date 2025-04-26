# Etapa 1: build
FROM gradle:8.7-jdk17 AS build

WORKDIR /home/gradle/project

COPY . .

RUN gradle build -x test

# Etapa 2: imagem final
FROM openjdk:17-jdk-slim

WORKDIR /app

# AQUI AJUSTADO:
COPY --from=build /home/gradle/project/build/libs/paymentservice-*.jar app.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "app.jar"]
