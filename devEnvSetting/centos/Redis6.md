# CentOS8下Redis6安装与配置

```bash
#下载6.0.4版本的redis
$[/opt] wget http://download.redis.io/releases/redis-6.0.4.tar.gz
#解压
$[/opt] tar -zxvf redis-6.0.4.tar.gz
#新建目录
$[/opt] mkdir redis
#进入源代码目录并安装
$[/opt] cd redis-6.0.4/
$[/opt/redis-6.0.4] make PREFIX=/opt/redis install
#修改系统环境变量 /etc/profile ，在文件末尾添加
export REDIS_HOME=/opt/redis
export PATH=$PATH:$REDIS_HOME/bin
$[/opt/redis-6.0.4] source /etc/profile
#生成redis服务化管理脚本,利用redis自带的工具，可以生成redis服务化管理脚本，切换至redis解压目录下的utils文件夹
$[/opt/redis-6.0.4/utils] ./install_server.sh  #指定各个文件所在位置

#查看redis服务状态
service redis_6379 status
#停止redis服务
service redis_6379 stop
#如果stop需要auth那么可以直接用kill -9 pid来终止服务

#使用指定的配置文件来开启redis-server
redis-server /etc/redis/redis.conf
#登陆redis-cli
auth password

#修改redis配置文件
nano /etc/redis/redis.conf

bind 0.0.0.0
requirepass yourpassword
protected-mode no
daemonize yes

#在java中使用Jedis远程连接时需要指定用户和密码，用户默认为default
<bean id="redisDao" class="org.seckill.dao.RedisDao">
        <constructor-arg name="host" value="*.*.*.*" />
        <constructor-arg name="port" value="6379" />
        <constructor-arg name="user" value="default" />
        <constructor-arg name="password" value="mima" />
</bean>
```

```bash
#如果因为systemd报错注释掉./install_server.sh中的这些就可以了
#bail if this system is managed by systemd
#_pid_1_exe="$(readlink -f /proc/1/exe)"
#if [ "${_pid_1_exe##*/}" = systemd ]
#then
#       echo "This systems seems to use systemd."
#       echo "Please take a look at the provided example service unit files in this directory, and adapt and install them. Sorry!"
#       exit 1
#fi
```

```java
最后起服务redis-server /etc/redis.conf,刚开始报“Bad directive or wrong number of arguments”，错误的指令或参数个数，
可以使用
redis-server -v
查看redis的版本，看是否与当前redis保持一致
进入根目录进行查找
$/ : find . -name "*redis*"
```

