# [Ubuntu18.04下安装配置Redis](https://www.cnblogs.com/dylancao/p/12268042.html)

## 注意：

1. 如果是在云服务器中配置Redis，记得修改/etc/redis/redis.conf文件中的bind 127.0.0.1为bind 0.0.0.0
2. 记得放开安全组的6379端口