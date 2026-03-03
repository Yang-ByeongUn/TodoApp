#!/bin/bash

echo "--------------- 서버 배포 시작 -----------------"
docker stop todo-server || true
docker rm todo-server || true
docker pull 151625667874.dkr.ecr.ap-northeast-2.amazonaws.com/todo-server-ecr:latest
docker run -d --name todo-server -p 8080:8080 151625667874.dkr.ecr.ap-northeast-2.amazonaws.com/todo-server-ecr:latest
echo "--------------- 서버 배포 끝 -----------------"