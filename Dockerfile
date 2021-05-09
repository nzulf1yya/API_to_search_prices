FROM openjdk:11
EXPOSE 8080
COPY target/restapi-0.1.jar /restapi.jar

CMD ["java","-jar","/restapi.jar"]