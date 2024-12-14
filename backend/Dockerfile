FROM maven
WORKDIR /app
COPY pom.xml .
COPY src src
RUN mvn compile
EXPOSE 5000
CMD ["mvn", "spring-boot:run"]
