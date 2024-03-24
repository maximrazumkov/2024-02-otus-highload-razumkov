FROM bellsoft/liberica-openjdk-alpine:21
ENV TZ Europe/Moscow
ARG POSTGRES_USER
ARG POSTGRES_PASSWORD
ARG POSTGRES_DB
ARG POSTGRES_HOST
ARG POSTGRES_PORT
VOLUME /opt/highload/
WORKDIR /opt/highload/
COPY ./ /opt/highload/
RUN ./gradlew build
EXPOSE 8080
CMD java -jar \
    -Dspring.datasource.url=jdbc:postgresql://${POSTGRES_HOST}:${POSTGRES_PORT}/${POSTGRES_DB} \
    -Dspring.datasource.username=${POSTGRES_USER} \
    -Dspring.datasource.password=${POSTGRES_PASSWORD} \
    build/libs/highload-0.0.1-SNAPSHOT.jar