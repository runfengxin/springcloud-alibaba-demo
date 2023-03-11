#!/bin/bash
#平滑关闭和启动springboot
#设置端口
SERVER_PORT="8081"
#设置应用名称
JAR_NAME="order-service"
#设置JAVA启动参数
JAVA_OPTIONS="-server -Xms512M -Xmx512M -Dserver.port=$SERVER_PORT"

cd /var/log
mkdir -p $JAR_NAME
#Actuator 方式远程关闭应用
CURRENT_PORT=$(lsof -i:"$SERVER_PORT" | wc -l)
if [ "$CURRENT_PORT" -gt "0" ];then
    curl -X POST "http://localhost:$SERVER_PORT/actuator/shutdown"
fi
echo ""

#循环遍历应用端口释放被使用，作为应用运作状态的标志
echo "close old app starting..."
UP_STATUS=1
while(( $UP_STATUS ))
do
    UP_STATUS=$(lsof -i:"$SERVER_PORT" | wc -l)
done
echo "\n   close old app ending..."
echo "start new app"
#非挂起方式启动应用，并且跟踪启动日志文件
cd /var/lib/workspace
nohup /home/jdk/bin/java -jar $JAVA_OPTIONS "$JAR_NAME".jar > /var/log/$JAR_NAME/$SERVER_PORT.log  &
echo "app starting"