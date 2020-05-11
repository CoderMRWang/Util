#从java的哪里的jdk进行
FROM dockerfile/java:oracle-java8
#COPY 从当前目录中的文件复制到 目标路径位置
VOLUME /tmp
ADD utils.jar /opt/utils/
EXPOSE 8080
WORKDIR /opt/utils/
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-Xms512m", "-Xmx1g", "-jar", "utils.jar"]
