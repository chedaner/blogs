一.端口操作

​	(1)查看指定端口

​		netstat -ano | findstr "端口号"

​	(2)关闭指定端口

​		taskkill -f -pid PID

二.nginx操作

​	(1)启动

​		start nginx

​	(2)停止

​		nginx.exe -s stop

​		nginx.exe -s quit

​	(3)重新载入Nginx

​		nginx.exe -s reload