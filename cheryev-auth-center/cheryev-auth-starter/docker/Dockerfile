#FROM 10.7.116.6:31104/tsf_yonyou/jdk1.8:latest

RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo "Asia/Shanghai" > /etc/timezone

ENV APP_LOG_PATH /ap/faw/logs
RUN mkdir -p $APP_LOG_PATH

ENV APPLICATION_NAME leadcenter-collect-service-0.1-SNAPSHOT.jar

ADD $APPLICATION_NAME /data/tsf/

COPY run.sh /data/tsf/

##COPY filebeat.yml /opt/filebeat-6.7.1-linux-x86_64

#应用需要暴露的端口
EXPOSE 12001

WORKDIR /data/tsf
CMD ["sh", "-c", "cd /data/tsf; sh run.sh $APPLICATION_NAME /data/tsf"]
