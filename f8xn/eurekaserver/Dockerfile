FROM openjdk:8-jdk-alpine
#docker pull daocloud.io/library/java:8-jre-alpine
#docker pull openjdk:8-jdk-alpine
#docker pull daocloud.io/java:8
VOLUME /tmp
#这里的 /tmp 目录就会在运行时自动挂载为匿名卷，任何向 /tmp 中写入的信息都不会记录进容器存储层
#临时目录,用后即删/var/lib/docker/tmp/docker-builder955996867/target/eurekaserver.jar
COPY target/eurekaserver.jar eurekaserver.jar
#也可以用COPY *.jar eurekaserver.jar
#也可以用ADD *.jar eurekaserver.jar
#默认复制target/*.jar到/var/lib/docker/tmp/docker-builder955996867/target/eurekaserver.jar
#RUN bash -c "touch /eurekaserver.jar"
#bash方式执行，使eurekaserver.jar可访问,加了报错
EXPOSE 8761
#声明运行时容器提供服务端口，这只是一个声明，在运行时并不会因为这个声明应用就会开启这个端口的服务,加了之后可以使用-p指定随机端口
ENTRYPOINT ["java","-jar","/eurekaserver.jar"]
#指定容器启动程序及参数   <ENTRYPOINT> "<CMD>"
#也可以用ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/eurekaserver.jar" ]
#ADD *.jar eurekaserver.jar，把本机的D:\workspace\idea\springcloud\eurekaserver\target\eurekaserver.jar复制到docker服务器31，并命名为eurekaserver.jar
#在centos的/root目录下执行java -jar eurekaserver.jar运行eurekaserver.jar包。访问http://10.0.0.31:8761/

#FROM openjdk:8-jdk-alpine
#VOLUME /tmp
#ARG JAR_FILE
#COPY ${JAR_FILE} eurekaserver.jar
#ENTRYPOINT ["java","-jar","/eurekaserver.jar"]

#docker tag f8xn/eurekaserver:latest 106.13.100.117/library/f8xn/eurekaserver:latest
#docker push 106.13.100.117/library/f8xn/eurekaserver:latest