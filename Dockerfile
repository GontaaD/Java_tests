FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY . .

CMD ["mvn", "clean", "test", "-DsuiteXmlFile=src/test/resources/testng.xml"]
