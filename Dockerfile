FROM openjdk:11
COPY target/university-0.0.1-SNAPSHOT.jar university-0.0.1.jar
ENTRYPOINT ["java","-jar","/university-0.0.1.jar"]