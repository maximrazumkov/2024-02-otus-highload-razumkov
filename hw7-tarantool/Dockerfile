FROM bellsoft/liberica-openjdk-alpine:21
ENV TZ Europe/Moscow
ARG TARANTOOL_HOST
ARG TARANTOOL_PORT
VOLUME /opt/highload/
WORKDIR /opt/highload/
COPY ./build/libs/hw7-tarantool.jar /opt/highload/
RUN ls -l
EXPOSE 8082
CMD java -jar \
    -Dtarantool.uri=${TARANTOOL_HOST} \
    -Dtarantool.port=${TARANTOOL_PORT} \
    -DuserService.url=${USER_SERVICE_URL} \
    hw7-tarantool.jar