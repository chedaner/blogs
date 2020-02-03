#### 1.java

//安装
sudo apt-get update
sudo apt-get install openjdk-8-jdk
	
//验证：
Java -version

（环境变量配置：

RABBITMQ_HOME=/usr/local/rabbitmq
JAVA_HOME=/usr/java
CLASSPATH=.:$JAVA_HOME/lib/tools.jar
PATH=$JAVA_HOME/bin:/usr/local/erlang/bin:$RABBITMQ_HOME/sbin:$PATH
export JAVA_HOME CLASSPATH PATH RABBITMQ_HOME

）
	

#### 2.mysql

//安装
sudo apt-get update  
sudo apt-get install mysql-server  

//验证
service mysql start
service mysql stop
sudo netstat -tap | grep mysql
mysql -u root -p

//设置远程连接
(1)所有用户均可访问
sql>use mysql;   

mysql>update user set host = '%' where user = 'root';  

mysql>select host, user from user; 

(2)注释bind-address    = 127.0.0.1
/etc/mysql/mysql.conf.d/mysqld.cnf
重新启动：
/etc/init.d/mysql stop
/etc/init.d/mysql start



#### 3.tomcat

apache-tomcat-8.0.47.tar.gz

//解压
tar -zxvf apache-tomcat-8.0.47.tar.gz



#### 4.nginx

https://www.cnblogs.com/hanfan/p/10393913.html



#### 5.redis

https://blog.csdn.net/xx345385463/article/details/90695629
https://blog.csdn.net/qq_35992900/article/details/82950157



#### 6.rabbitmq

https://blog.csdn.net/forever_insist/article/details/80887444

配置默认端口： /usr/local/rabbitmq/etc/rabbitmq/rabbitmq.config 
 [{rabbitmq_management,[{listener, [{port, 8083}]}]},{rabbit, [{loopback_users, []},{tcp_listeners, [{"172.17.118.226", 8084}]}]}]. 