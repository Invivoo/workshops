FROM dockerfile/java:oracle-java8

MAINTAINER Daniel Lavoie <daniel.lavoie@invivoo.com>

ADD secured-rest-api-docker.jar /secured-rest-api-docker/secured-rest-api-docker.jar
ADD run.sh /run.sh

RUN chmod +x /*.sh

VOLUME [/"data"]

EXPOSE 8080

CMD ["/run.sh"]