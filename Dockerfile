FROM openjdk:11
EXPOSE 1015
ADD /target/webcharmi.jar webcharmi.jar
ENTRYPOINT ["java", "-jar", "/webcharmi.jar"]