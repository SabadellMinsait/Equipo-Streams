FROM openjdk:17-jdk-alpine
WORKDIR /app

COPY pom.xml /app
COPY . .


RUN sed -i -e 's/\r$//' ./mvnw
RUN ./mvnw clean install -DskipTests
RUN ./mvnw clean package -DskipTests


EXPOSE 8080

ENTRYPOINT ["java","-jar","./target/pacientes-0.0.1-SNAPSHOT.jar"]