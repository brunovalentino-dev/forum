FROM eclipse-temurin:17-jdk

EXPOSE 8080

ADD /target/forum-1.0.0-SNAPSHOT.jar forum.jar

CMD start.sh
