FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY gradlew .

RUN ./gradlew --no-daemon dependencies

COPY src src
COPY config config

RUN ./gradlew --no-daemon shadowJar

EXPOSE 7070

CMD java $JAVA_OPTS -jar build/libs/app-1.0-SNAPSHOT-all.jar
