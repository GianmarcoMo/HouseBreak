FROM openjdk:8-alpine
RUN mkdir /app
COPY ./target/HouseBreak-2.jar /app
WORKDIR /app
ENTRYPOINT ["java", "-jar", "HouseBreak-2.jar"]
