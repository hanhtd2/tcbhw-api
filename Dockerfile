FROM maven

RUN mkdir /root/tcbhw-api
WORKDIR /root/tcbhw-api
COPY . .
RUN mvn clean install -DskipTests
CMD ["java", "-jar", "target/tcbhw-api-spring-boot.jar"]
EXPOSE 8080