#!/bin/bash
JAR_NAME="order-service"
WORKSPACE="/var/lib/workspace/$JAR_NAME"

chmod +x startup.sh shutdown.sh monitor.service
cp -p startup.sh $WORKSPACE
cp -p shutdown.sh $WORKSPACE
cp -p monitor.service /etc/systemd/system/$JAR_NAME.service
cd ../target
cp $JAR_NAME.jar $WORKSPACE

systemctl daemon-reload
systemctl start $JAR_NAME.service
systemctl enable $JAR_NAME.service
