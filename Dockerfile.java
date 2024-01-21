        FROM maven:3.8.5-openjdk-17-slim as build
        COPY . .
        RUN mvn clean package -DskipTests

        FROM openjdk:17.0.1-jdk-slim
        COPY --from=build /target/job-search-platform-0.0.1-SNAPSHOT.jar job-search-platform.jar
        EXPOSE 8080

        ENTRYPOINT ["java","-jar","job-search-platform.jar"]