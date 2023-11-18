# Используйте официальный образ Maven с Java 17 для сборки вашего приложения
FROM maven:3.8.4-openjdk-17 AS build

# Установите рабочую директорию в /app
WORKDIR /app

# Копируйте файлы вашего проекта в контейнер
COPY . .

# Соберите приложение
RUN mvn package -DskipTests

# Используйте официальный образ OpenJDK 17 для запуска вашего приложения
FROM openjdk:17-jdk-slim

# Установите рабочую директорию в /app
WORKDIR /app

# Копируйте jar файл из стадии сборки в текущую стадию
COPY --from=build /app/target/englishbot-0.0.1-SNAPSHOT.jar app.jar

COPY src/main/resources/keystore.jks keystore.p12

# Откройте порт, на котором работает ваше приложение
EXPOSE 8080
EXPOSE 8443

# Запустите приложение
ENTRYPOINT ["java","-Djavax.net.ssl.trustStore=/app/keystore.p12","-Djavax.net.ssl.trustStorePassword=snwm1337","-jar","app.jar"]