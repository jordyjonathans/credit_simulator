# FROM openjdk:18
# WORKDIR /
#
# ADD ./target/credit_simulator-1.0-SNAPSHOT.jar /credit_simulator.jar
# ADD ./target/credit_simulator.txt /credit_simulator.txt
#
#
# CMD ["java", "-jar","credit_simulator.jar","credit_simulator.txt"]

FROM ubuntu:latest

RUN apt-get update && \
    apt-get install -y openjdk-18-jdk ca-certificates-java && \
    apt-get clean && \
    update-ca-certificates -f
ENV JAVA_HOME /usr/lib/jvm/java-18-openjdk-amd64/

RUN export JAVA_HOME

ADD credit_simulator.jar /credit_simulator.jar
ADD credit_simulator.txt /credit_simulator.txt

CMD ["java", "-jar","credit_simulator.jar","credit_simulator.txt"]
