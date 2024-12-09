FROM bellsoft/liberica-openjdk-alpine:21
ENV TZ Europe/Moscow
ARG POSTGRES_USER
ARG POSTGRES_PASSWORD
ARG POSTGRES_DB
ARG POSTGRES_HOST_MASTER
ARG POSTGRES_PORT_MASTER
ARG POSTGRES_HOST_SLAVE1
ARG POSTGRES_PORT_SLAVE1
ARG POSTGRES_HOST_SLAVE2
ARG POSTGRES_PORT_SLAVE2
ARG REDIS_HOST
ARG REDIS_PORT
ARG KAFKA_BOOTSTRAP_SERVERS
VOLUME /opt/highload/
WORKDIR /opt/highload/
COPY ./build/libs/highload-0.0.1-SNAPSHOT.jar /opt/highload/
EXPOSE 8080
CMD java -jar \
    -Dspring.datasource.master.url=jdbc:postgresql://${POSTGRES_HOST_MASTER}:${POSTGRES_PORT_MASTER}/${POSTGRES_DB} \
    -Dspring.datasource.master.username=${POSTGRES_USER} \
    -Dspring.datasource.master.password=${POSTGRES_PASSWORD} \
    -Dspring.datasource.slave1.url=jdbc:postgresql://${POSTGRES_HOST_SLAVE1}:${POSTGRES_PORT_SLAVE1}/${POSTGRES_DB} \
    -Dspring.datasource.slave1.username=${POSTGRES_USER} \
    -Dspring.datasource.slave1.password=${POSTGRES_PASSWORD} \
    -Dspring.datasource.slave2.url=jdbc:postgresql://${POSTGRES_HOST_SLAVE2}:${POSTGRES_PORT_SLAVE2}/${POSTGRES_DB} \
    -Dspring.datasource.slave2.username=${POSTGRES_USER} \
    -Dspring.datasource.slave2.password=${POSTGRES_PASSWORD} \
    -Dspring.kafka.producer.bootstrap-servers=${KAFKA_BOOTSTRAP_SERVERS} \
    -Dspring.kafka.consumer.bootstrap-servers=${KAFKA_BOOTSTRAP_SERVERS} \
    -Dspring.redis.host=${REDIS_HOST} \
    -Dspring.redis.port=${REDIS_PORT} \
    highload-0.0.1-SNAPSHOT.jar