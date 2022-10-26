# 基于java镜像创建新镜像
FROM khipu/openjdk17-alpine
# 作者
MAINTAINER yujiangjun
# 将jar包添加到容器中并更名为app.jar
ADD  build/libs/raytineOss-1.0-SNAPSHOT.jar /root/app/app.jar
# 运行jar包
ENTRYPOINT ["nohup","java","-jar","/root/app/app.jar","&"]