FROM adoptopenjdk/openjdk11:latest
WORKDIR /
ADD target/customer-0.0.1-SNAPSHOT.jar //
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/customer-0.0.1-SNAPSHOT.jar"]
