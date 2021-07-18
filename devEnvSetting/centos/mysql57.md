# mysql57安装与配置

```bash
#安装

#安装wget
yum install wget
#下载mysql rpm文件
wget http://dev.mysql.com/get/mysql80-community-release-el7-3.noarch.rpm
#安装mysql rpm文件
rpm -ivh mysql80-community-release-el7-3.noarch.rpm
#这时候会在 /etc/yum.repos.d/目录下生成两个文件mysql-community.repo和mysql-community-source.repo
#修改repo文件（安装5.7，不要8.0）
cd /etc/yum.repos.d/
nano mysql-community.repo
#将 [mysql57-community] 下的 enabled 设置为1，表示打开5.7
#将 [mysql80-community] 下的 enabled 设置为0，表示关闭8.0
#如果有错误，使用下面语句
yum module disable mysql
#使用yum安装mysql57
yum -y install mysql-community-server
#查看mysql版本
mysql --version
#开启mysqld服务
systemctl start mysqld.service
#查看mysqld服务状态
systemctl status mysqld.service
```

```bash
#配置mysql

#查看生成的初始密码:
sudo grep 'temporary password' /var/log/mysqld.log  
#使用初始密码进行登录:
mysql -u root -p
#登录成功之后设置新密码:设置的新密码必须包含大写字母、小写字母、数字、标点符号，且密码长度至少为 8
ALTER USER 'root'@'localhost' IDENTIFIED BY 'MyNewPass4!';
#设置 MySQL开机启动,CentOS 7 使用 systemctl 命令工具操作服务:MySLQ 安装完成之后运行的服务名称为: mysqld
#开机启动
systemctl enable mysqld
#设置远程连接
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'yourNewPassword' WITH GRANT OPTION;
#刷新权限
FLUSH PRIVILEGES;

#配置文件位置
nano /etc/my.cnf
#添加如下语句，修改字符集
[mysqld]
character-set-server=utf8
init_connect='SET NAMES utf8'
[client]
default-character-set=utf8
[mysql]
default-character-set=utf8

#查看字符集是否修改成功
show variables like '%character%';
```

