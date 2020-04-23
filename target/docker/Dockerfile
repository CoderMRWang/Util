FROM dockerfile/java:oracle-java8

VOLUME /tmp
ADD utils.jar /opt/utils/
EXPOSE 8080
WORKDIR /opt/utils/
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-Xms512m", "-Xmx1g", "-jar", "utils.jar"]
