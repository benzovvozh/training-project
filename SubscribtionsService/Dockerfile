FROM gradle:8.9-jdk21 AS build
WORKDIR /home/gradle/project

COPY . .

RUN gradle bootJar --no-daemon

FROM eclipse-temurin:21-jre-alpine

# Рабочая директория внутри контейнера
WORKDIR /opt/app

# Копируем JAR из Gradle build
COPY --from=build /home/gradle/project/build/libs/*.jar app.jar

# Команда запуска
ENTRYPOINT ["java","-jar","app.jar"]