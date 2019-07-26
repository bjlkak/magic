FROM openjdk:8
ADD /target/blee-core-1.0.0.jar /data/blee-core-1.0.0.jar
ADD config.yml /data/config.yml

WORKDIR /data/

CMD java -jar blee-core-1.0.0.jar db migrate /data/config.yml

CMD java -jar blee-core-1.0.0.jar server /data/config.yml

EXPOSE 8080
