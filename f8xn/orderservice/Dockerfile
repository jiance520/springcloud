FROM openjdk:8-jdk-alpine
#docker pull daocloud.io/library/java:8-jre-alpine
#docker pull openjdk:8-jdk-alpine
#docker pull daocloud.io/java:8
VOLUME /tmp
#这里的 /tmp 目录就会在运行时自动挂载为匿名卷，任何向 /tmp 中写入的信息都不会记录进容器存储层
#临时目录,用后即删/var/lib/docker/tmp/docker-builder955996867/target/orderservice.jar
COPY target/orderservice.jar orderservice.jar
#也可以用COPY *.jar orderservice.jar
#也可以用ADD *.jar orderservice.jar
#默认复制target/*.jar到/var/lib/docker/tmp/docker-builder955996867/target/orderservice.jar
#RUN bash -c "touch /orderservice.jar"
#bash方式执行，使orderservice.jar可访问,加了报错
EXPOSE 8781
#声明运行时容器提供服务端口，这只是一个声明，在运行时并不会因为这个声明应用就会开启这个端口的服务,加了之后可以使用-p指定随机端口
ENTRYPOINT ["java","-jar","/orderservice.jar"]
#指定容器启动程序及参数   <ENTRYPOINT> "<CMD>"
#也可以用ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/orderservice.jar" ]
#ADD *.jar orderservice.jar，把本机的D:\workspace\idea\springcloud\orderservice\target\orderservice.jar复制到docker服务器31，并命名为orderservice.jar
#在centos的/root目录下执行java -jar orderservice.jar运行orderservice.jar包。访问http://10.0.0.31:8761/

#FROM openjdk:8-jdk-alpine
#VOLUME /tmp
#ARG JAR_FILE
#COPY ${JAR_FILE} orderservice.jar
#ENTRYPOINT ["java","-jar","/orderservice.jar"]

#docker tag f8xn/orderservice:latest 106.13.100.117/library/f8xn/orderservice:latest
#docker push 106.13.100.117/library/f8xn/orderservice:latest