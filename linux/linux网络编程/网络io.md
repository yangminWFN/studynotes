# linux网络编程

## 1、基本网络连接模型

```c
//基本连接模型
server.c
    sockfd=sock()
    bind(sockfd,addr)
    listen(sockfd)
    clientfd=accept(sockfd) //阻塞
    recv(clientfd,buffer) //接收数据--阻塞
    
client.c
    sockfd=sock()
    connect(sockfd,addr);
	send(sockfd,msg) //发送数据
```

上述为一个tcp连接的基本步骤，是一个同步阻塞IO过程。为了解决 `recv` 接收数据时的阻塞问题，可以使用多线程的方式，服务端每次得到一个新的连接后，开启一个线程来处理数据IO操作，这种方式叫伪非阻塞IO。

数据从网卡到达用户空间分两步：

1. 从网卡拷贝到内核缓冲区--阻塞

2. 从内核缓冲区拷贝到用户空间--阻塞

   

为了解决读取数据时从==网卡拷贝到内核缓冲区阻塞==的问题，可以采用由操作系统提供的一个非阻塞read函数，每次使用系统调用的方式来调用该函数，如果内核缓冲区数据准备好了，那么就通知用户可以准备开始读取了，如果没准备好就返回-1。