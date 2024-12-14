FROM maven
WORKDIR /app
COPY pom.xml .
COPY src src
# RUN mvn compile
RUN ls
RUN mvn -X spring-boot:run
EXPOSE 5000
