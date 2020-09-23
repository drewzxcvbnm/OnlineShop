FROM gradle:jre14 AS FIRST
COPY . /app
WORKDIR /app
RUN gradle clean assemble

FROM openjdk:14 AS SECOND
EXPOSE 8080
COPY --from=0 /app/build/libs/*.jar /app/
CMD java -jar /app/*.jar

