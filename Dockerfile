FROM maven:3.9.11-eclipse-temurin-21-noble

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn dependency:go-offline

RUN apt-get update && \
    apt-get install -y wget unzip && \
    wget https://github.com/allure-framework/allure2/releases/download/2.27.0/allure-2.27.0.zip && \
    unzip allure-2.27.0.zip -d /opt/ && \
    ln -s /opt/allure-2.27.0/bin/allure /usr/bin/allure && \
    rm allure-2.27.0.zip

EXPOSE 8080

CMD ["sh", "-c", "\
  mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml && \
  allure generate target/allure-results -o target/allure-report --clean && \
  allure open target/allure-report --port 8080 --host 0.0.0.0"]
