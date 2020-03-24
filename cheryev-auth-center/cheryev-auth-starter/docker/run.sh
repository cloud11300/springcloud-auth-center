#echo "start filebeat now ..."
#nohup /opt/filebeat-6.7.1-linux-x86_64/filebeat -e -c /opt/filebeat-6.7.1-linux-x86_64/filebeat.yml > /opt/filebeat.log &

#JAVA_OPTS="-server -Xms2g -Xmx2g -Dconfig.dir=classpath:config -Dlogs.dir=/data/tsf -Dlogger.device=file -Dspring.profiles.active=dev "
JAVA_OPTS="-server -Xms2g -Xmx2g -Dconfig.dir=classpath:config -Dlogs.dir=/data/tsf -Dlogger.device=file"

echo "219.141.178.56 test.dealer.interface.autohome.com.cn" >> /etc/hosts
echo "219.141.178.56 f2.dealer.autohome.com.cn" >> /etc/hosts

export PATH=$PATH:/data/tsf
default_log_path="/data/tsf_default"
stout_log_path="/data/tsf_std/stdout/logs"
stout_log="$stout_log_path/sys_log.log"
echo "para1 is"$1
echo "para2 is"$2
echo $stout_log_path
echo $stout_log
mkdir -p $stout_log_path
if [ ! -n "$2" ] ;then
    echo "you have not input logpath!"
else
        mkdir -p $default_log_path
        cd $2
        cp $1 $default_log_path
        sleep 5
        cd $default_log_path
fi

java ${JAVA_OPTS} -jar $1 > $stout_log 2>&1
