FROM ubuntu:latest

WORKDIR /app
COPY . .

RUN apt update -y
RUN apt install openjdk-11-jdk -y
RUN apt install maven -y
RUN mvn install

EXPOSE 9900

CMD [ "mvn", "spring-boot:run" ]