FROM eclipse-temurin:17-jdk

EXPOSE 8080

ADD /target/forum-1.0.0-SNAPSHOT.jar forum.jar

ENV JAVA_OPTS="-XX:+UseContainerSupport -Xmx300m -Xss512k -XX:CICompilerCount=2 -Dserver.port=$PORT -Dspring.profiles.active=prod"

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar forum.jar"]
