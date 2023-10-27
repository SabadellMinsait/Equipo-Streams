FROM openjdk:17-jdk-alpine
WORKDIR /app

COPY pom.xml /app
COPY . .


RUN sed -i -e 's/\r$//' ./mvnw
RUN ./mvnw clean install -DskipTests
RUN ./mvnw clean package -DskipTests


EXPOSE 8092

ENTRYPOINT ["java","-jar","./target/Doctores-0.0.1-SNAPSHOT.jar"]