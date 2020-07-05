From gradle:jre14
COPY . /app
WORKDIR /app
EXPOSE 8080
RUN gradle assemble
ENTRYPOINT gradle runJar

