# autossh内网穿透

目的：使用一台公网服务器（阿里云：106.15.108.171），ssh连接到实验室中的内网服务器（datanode1：192.168.2.151）执行命令

步骤如下：

1. 在内网机上：修改sshd配置/etc/ssh/sshd_config

   ```bash
   GatewayPorts yes
   ```

2. 在内网机上：为了能够断开自动重连，安装autossh(ubuntu)

   ```bash
   sudo apt-get install autossh
   ```

3. 在内网机上：先配置内网机免密访问公网机

   ```bash
   # 免密配置
   #（没有执行密钥生成）
   ssh-keygen
   # 将公钥拷贝到公网机中
   ssh-copy-id root@106.15.108.171
   ```

4. 在内网机上：与公网服务器建立连接通道

   ```bash
   autossh -M [内网任意未使用端口] -fNR [公网服务器开放端口]:127.0.0.1:[本地sshd端口] [公网服务器用户名]@[公网ip]
   
   # 例如：
   autossh -M 4010 -fNR 4000:127.0.0.1:22 root@106.15.108.171
   # 这里的-f表示在后台连接ssh
   # 表示：在内网机的4010端口上检测是否断开，4000是公网开放端口，22是本地的sshd端口
   ```

5. 可以在公网机上：查看端口监听状态

   ```bash
   lsof -i:4000
   ```

6. 在公网机上：ssh连接内网机

   ```bash
   ssh hadoop@localhost -p 4000
   # 输入密码
   ```

   

