FROM bellsoft/liberica-openjdk-alpine:21
ENV TZ Europe/Moscow
ARG MONGO_HOST
ARG MONGO_PORT
ARG MONGO_DB_NAME
VOLUME /opt/highload/
WORKDIR /opt/highload/
COPY ./build/libs/hw5-sharding.jar /opt/highload/
RUN ls -l
EXPOSE 8080
CMD java -jar \
    -Dspring.data.mongodb.uri=mongodb://${MONGO_HOST}:${MONGO_PORT}/${MONGO_DB_NAME} \
    hw5-sharding.jar