# Ubuntu下Mysql5.7的配置与安装

## Mysql5.7安装

1. 使用**sudo apt install mysql-server** 安装mysql5.7

2. 使用**sudo mysql**进入数据设置root账户的密码和权限

3. 使用**use mysql;**进入到名字叫mysql的数据库中

4. 查看并修改user表中的数据

   1. ```mysql
      select user,plugin,authentication_string from user;  //查看用户表数据
      ```

   2. ```mysql
      update user set plugin='mysql_native_password' where user='root' //修改认证方式为输入密码
      ```

   3.  ```mysql
      update user set authentication_string=PASSWORD('your_passworod') where user='root'; //修改为自己的密码
      ```

   4. ```mysql
      flush privileges; //刷新权限
      ```

5. 使用**exit**退出命令行模式

6. 使用**mysql -uroot -p**登陆mysql中

7. 查看mysql服务状况

   ```mysql
   > 查看mysql状态  sudo service mysql status
   > 启动mysql服务  sudo service mysql start
   > 停止mysql服务 sudo service mysql stop
   > 重启mysql服务 sudo service msyql restart
   ```

   

## Mysql5.7配置

1. 将默认字符集改为 **utf8mb4**

   ```
   在MySQL 5.5 之前，UTF-8编码只支持1-3个字节；
   从MySQL 5.5开始，可以支持4个字节UTF编码 utf8mb4 ，一个字符能够支持更多的字符集，也能够支持更多表情符号
   emoji表情为4个字节
   ```

2. 查看编码格式

   ```mysql
   show variables like '%char%';
   ```

3. 修改 **/etc/mysql/my.cnf**

   ```mysql
   !includedir /etc/mysql/conf.d/
   !includedir /etc/mysql/mysql.conf.d/
   [mysqld]
   # 设置3306端口
   port=3306
   # 设置mysql的安装目录
   # basedir=/usr/local/mysql
   # 设置mysql数据库的数据的存放目录
   # datadir= /var/lib/mysql
   # 允许最大连接数
   max_connections=200
   # 允许连接失败的次数。这是为了防止有人从该主机试图攻击数据库系统
   max_connect_errors=10
   # 服务端使用的字符集默认为UTF8
   character-set-server=utf8mb4
   init_connect = 'SET NAMES utf8mb4'
   character-set-client-handshake = FALSE
   collation-server=utf8mb4_general_ci
   #使用–skip-external-locking MySQL选项以避免外部锁定。该选项默认开启
   external-locking = FALSE
   # 创建新表时将使用的默认存储引擎
   default-storage-engine=INNODB
   # 默认使用“mysql_native_password”插件认证
   default_authentication_plugin=mysql_native_password
   
   [mysqld_safe]
   log-error=error.log
   #pid-file=mysqld.pid
   # 定义mysql应该支持的sql语法，数据校验
   sql_mode=NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES
   
   [mysql]
   # 设置mysql客户端默认字符集
   default-character-set=utf8mb4
   
   [client]
   # 设置mysql客户端连接服务端时默认使用的端口
   port=3306
   default-character-set=utf8mb4
   
   ```

4. 重启mysql服务 **service restart mysql**

5. 如果创建数据库和表时没有使用默认的字符集，那么还可以使用下面的语句来修改字符集

   ```mysql
    // 更改数据库字符集
   ALTER DATABASE DATABASE_NAME CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
    // 更改表字符集
   ALTER TABLE TABLE_NAME CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci; 
   ```

   