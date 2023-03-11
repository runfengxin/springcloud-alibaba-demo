#!/bin/bash
#平滑关闭和启动springboot
#设置端口
SERVER_PORT="8081"
#Actuator 方式远程关闭应用
CURRENT_PORT=$(lsof -i:"$SERVER_PORT" | wc -l)
if [ "$CURRENT_PORT" -gt "0" ];then
    curl -X POST "http://localhost:$SERVER_PORT/actuator/shutdown"
fi
echo "shutdown successful!!!"