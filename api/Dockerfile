FROM openjdk:17-jdk-slim

WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

RUN ./gradlew build

# 빌드된 JAR 파일을 실행
CMD ["java", "-jar", "build/libs/api-0.0.1-SNAPSHOT.jar"]
