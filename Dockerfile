FROM openjdk:11-jdk
#Add group and user which are both called "spring"
RUN groupadd spring && useradd --gid spring spring
#Make docker user user spring in group spring ?
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]