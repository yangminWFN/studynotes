# Ubuntu全局和用户环境配置

1. **/etc/profile全局的，随系统启动设置【设置这个文件是一劳永逸的办法】**
2. /root/.profile和/home/myname/.profile只对当前窗口有效。
3. **/root/.bashrc和 /home/yourname/.bashrc随系统启动，设置用户的环境变量**【平时设置这个文件就可以了】



## 修改PATH变量的三种方式

1. 控制台中： $PATH="$PATH:/my_new_path"  （关闭shell，会还原PATH）

2. 修改 **/etc/profile** 文件，使用$sudo gedit /etc/profile 在里面加入: exportPATH="$PATH:/my_new_path"

3. 修改 **~/.bashrc** 文件

   $ sudo gedit /root/.bashrc

   在里面加入：

   export PATH="$PATH:/my_new_path"

   后两种方法一般需要重新注销系统才能生效，最后可以通过echo命令测试一下：

   $ echo $PATH

## Ubuntu使用apt-get install 安装的软件位置

1. 下载的软件的存放位置：**/var/cache/apt/archives**

2. 安装后软件的默认位置：**/usr/share**

3. 可执行文件位置：**/usr/bin**

4. 配置文件位置：**/etc**

5. lib文件位置：**/usr/lib**