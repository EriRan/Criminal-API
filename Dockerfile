#Assumptions: Jar has already been built

#Initialize deployment
FROM openjdk:11-jdk

#Copy the jar to the container, extract it and then remove the jar file
COPY target/*.jar .
RUN jar -xf *.jar
RUN rm *.jar

#The extracted jars should contain folders BOOT-INF and META-INF
#Use unpacked jar folders to store dependencies in different folders for improved performance
RUN mv BOOT-INF/classes /app
RUN mv BOOT-INF/lib /app/lib
RUN mv META-INF /app/META-INF

#Add group and user which are both called "spring"
RUN groupadd spring && useradd --gid spring spring
#Make docker user user spring in group spring ?
USER spring:spring

ENTRYPOINT ["java", "-cp", "app:app/lib/*", "fi.eriran.graphqltraining.GraphqlTrainingApplication"]