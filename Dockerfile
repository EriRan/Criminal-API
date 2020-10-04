FROM openjdk:11-jdk
#Add group and user which are both called "spring"
RUN groupadd spring && useradd --gid spring spring
#Make docker user user spring in group spring ?
USER spring:spring
#Use unpacked jar folders to store dependencies in different folders for improved performance
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java", "-cp", "app:app/lib/*","fi.eriran.graphqltraining.GraphqlTrainingApplication"]