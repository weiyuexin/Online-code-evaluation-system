# 基础镜像使用Java8
FROM java:8
# 作者
MAINTAINER wyx
VOLUME /tmp
# 将jar包添加到容器并更名
ADD Online-code-evaluation-system-1.0.0.jar henu_oj.jar
# 运行jar包
RUN bash - C 'touch /henu_oj.jar'
ENTRYPOINT ["java","-jar","/henu_oj.jar"]
# 暴露端口
EXPOSE 8080