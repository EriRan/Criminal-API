FROM openjdk:11-jdk
RUN groupadd spring && useradd --groups spring springuser
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]