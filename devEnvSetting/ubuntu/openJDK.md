# Ubuntu下配置OpenJDK1.8

1. 配置JAVA_HOME,JRE_HOME,PATH，配置文件的位置：/etc/profile
2. 在内容中加入下面的：
   		export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
   		export JRE_HOME=${JAVA_HOME}/jre   
   		export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib  
   		export PATH=${JAVA_HOME}/bin:$PATH
   		执行完之后，source /etc/profile ，使变动生效，不需用重启。
3. 用echo $JAVA_HOME检查是否配置成功