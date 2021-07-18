# CentOS8下docker的安装与配置

```bash
#安装docker依赖
[$ ~] yum -y update
[$ ~] yum install -y epel-release
[$ ~]yum install https://download.docker.com/linux/fedora/30/x86_64/stable/Packages/containerd.io-1.2.6-3.3.fc30.x86_64.rpm
[$ ~] yum install -y yum-utils device-mapper-persistent-data lvm2
[$ ~] yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo

#安装docker
[$ ~] yum install -y docker-ce docker-ce-cli

#启动docker
[$ ~] systemctl start docker

#设置开机自启
[$ ~] systemctl enable docker

#使用docker命令
[$ ~] docker images
```

==默认安装的docker目录在 `/var/lib/docker` 里==

==docker的默认配置文件在安装后是没有的，需要自己在`/etc/docker/`中新建一个deamon.json文件==

在deamon.json文件中配置阿里云镜像

```json
{
"registry-mirrors": ["https://xisih51q.mirror.aliyuncs.com"]
}
```

可以参考官方文档：https://docs.docker.com/engine/install/centos/

```yml
sever: 
	name: yangmin
	
```

