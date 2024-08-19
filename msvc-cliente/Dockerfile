ARG MSVC_NAME=msvc-cliente

FROM adoptopenjdk/openjdk11:jdk-11.0.11_9-alpine-slim as builder

ARG MSVC_NAME

WORKDIR /app/msvc-cliente

COPY ./pom.xml /app
COPY ./$MSVC_NAME/.mvn ./.mvn
COPY ./$MSVC_NAME/mvnw .
COPY ./$MSVC_NAME/pom.xml .

RUN pwd

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
#RUN ./mvnw dependency:go-offline

COPY ./$MSVC_NAME/src ./src

RUN ./mvnw clean package -DskipTests

FROM adoptopenjdk/openjdk11:jdk-11.0.11_9-alpine-slim

ARG MSVC_NAME

WORKDIR /app
RUN mkdir ./logs
ARG MSVC_NAME=msvc-cliente
ARG TARGET_FOLDER=/app/$MSVC_NAME/target
COPY --from=builder $TARGET_FOLDER/msvc-cliente-0.0.1-SNAPSHOT.jar .
ARG PORT_APP=8081
ENV PORT $PORT_APP
EXPOSE $PORT

CMD ["java","-jar","msvc-cliente-0.0.1-SNAPSHOT.jar"]