#  模板类模式

1、springboot实现初始化加载配置（实现缓存预热）

```java
         1、采用实现springboot ApplicationRunner
            该方法仅在SpringApplication.run(…)完成之前调用

         2、采用实现InitializingBean
            InitializingBean接口为bean提供了初始化方法的方式，它只包括afterPropertiesSet()方法。
            在spring初始化bean的时候，如果bean实现了InitializingBean接口，
            在对象的所有属性被初始化后之后才会调用afterPropertiesSet()方法

  一般采用实现InitializingBean的方式预热数据
```
考虑spring bean的生命周期，因为要从数据库中读取数据，所以需要调用Service方法，所以要在bean实例化后进行拦截 预热缓存。

2.我是使用redis中的Map数据结构进行存储的，还是按照数据库的设计，assesstype:id  {name:xxx,封面:xxx,简介},{answer:id, :{"score":xxx,"content":"xx"}}，{assess:asstype:dimension,{score:"xx","症状":"xxx"，意见:"意见"}}



利用枚举类，ApplicationContext ，以及模块类的设计模式

在枚举类中，定义所有心理测评的种类，serviceBeanName以及业务id，请求时根据 业务id，获取到对应的枚举类，再通过beanName，去applicationContext.getBean中获得具体的service，进行一个心理测评的评分。

在心理在线测评模块，由于心理测评的种类很多，不同种类的测评评分标准不同，算分的过程也不同，在观察了很多个表后，抽象出了，两种代表，第一种就是只有一个总体维度的，只要把每题分数相加，算出总分就可以得到对应的症状和意见报告，第二种是多维度的，一个维度对应一题或者多题，每个维度的各个题目得分按权值相加得到分数，但其实主要还是分为，查询各个题目得分，计算各个维度得分，封装成评测结果报告，只是计算各个维度得分不一样而已，我在父类定义算法的骨架，只需要实现计算各个维度方法就可以了。

设计模式六大原则

单一职责：一个类只负责一项职责

里氏替换：子类可以扩展父类功能，但不能修改父类原有功能

依赖倒置：细节应该抽象，而不是抽象依赖细节

接口隔离：一个类对另一个类的依赖应该建立最小的接口上

迪米特法则：避免类和类之间的耦合

开闭原则：对修改关闭，对扩展开放



单例，工厂，代理，模块方法，观察者模式

# 视频

### 整体介绍

实现了大视频文件的稳定传输，提供了分块上传和断点续传的功能，最初使用BIO RandomAccessFile 写入文件，再进行写入的性能优化，采用零拷贝等机制 将单块文件写入时间从 3ms~4ms 降低至0.7ms~0.9ms，单个请求平均等待时间从120ms 降低至 100ms，并且实现了简单的分布式存储服务，利用Zookeeper 以及 Dubbo SPI 扩展自定义集群策略 实现了基本的存储服务动态横向扩展。

在此期间了Tomcat是如何处理包含文件的请求，以及SpringMVC是怎么处理MulitpartRequest，包括一些IO模型和零拷贝技术，了解了Dubbo是如何引用服务和调用服务的，以及Dubbo SPI是如何实现自定义接口的扩展。



### 测试结果

使用BIO 写入 文件，单块写入1M文件 在 7 627 400 ns - 17 638 500 ns 波动  70M视频文件大概在 622 598 200 ns 622ms  

服务器1G 2核 

 2 020 889 ns - 22 20889 ns  2ms - 2.5ms之间

总共 153 194 090ns   153 ms

每个请求平均等待时间在 120-170ms 之间  

一共 10s



NIO 780900 ns - 1 271400 ns  61 614 300ns 

服务器1G 2核 

 760647 ns - 943288 ns   0.7ms - 0.9ms

总共 89 754 726ns 89ms

每个请求平均等待时间在 90-120ms 之间

一共 8s



服务器四核8G 2M带宽的情况下

使用BIO模式写入文件，单块写入1M文件 在3ms - 7ms左右波动

300M的BIM文件大概在1s - 1.5s写入完成

每个请求的平均等待时间为 80ms - 120ms之间

一共需要28s左右



NIO方式





### 相关知识

Http协议中文件上传 multipart/form-data 方式

HTTP 协议中规定了一种[**基于表单的文件上传方式（Form-based File Upload）**](https://link.juejin.cn?target=https%3A%2F%2Ftools.ietf.org%2Fhtml%2Frfc1867)。在 form 中定义一个 ENCTYPE 属性，值为 multipart/form-data，然后增加一个 type 为 file 的 `<input>` 标签。

![multiformdata_http.png](E:\秋招\isyslab_recruitment_tips\个人整理\cc\1ea8212aa9a94ce5a454c3a40836b381~tplv-k3u1fbpfcp-watermark.awebp)



从上图可以看到，HTTP header 部分没什么变化，只是在 Content-Type 中增加了一段 boundary 标签，但 payload 部分却完全不同

boundary 在 multipart/form-data 中作用是分隔表单的多个字段，在 payload 部分中，首尾两行各有一个 boundary，每个字段（part/item）之间也会有一个 boundary

Server 端在读取时，只需要先从 Content-Type 中拿到 boundary ，然后通过这个 boundary 去拆分 payload 部分就可以获取所有的字段。

每个字段的报文中，有一个 Content-Disposition字段，作为这个字段的 Header 部分。其中记录了当前字段名（name），如果是文件的话还会有一个 filename 属性，同时再下一行会附带一个 Content-Type 来标识文件的类型



### 设计细节

1.分块上传，多线程写入文件，采用NIO,RandomAcessFile，锁以及线程池技术

设计了一个分块上传的管理类，包含有内部类BlockFile维护每个具体的文件分块上传的信息。

String md5 , boolean[] status，String filename，AtomicInteger current，int size

文件md5信息，status记录每个分块上传成功否，filename 文件名以及对应在服务器的文件名，

current 维护当前上传的分块总数，size 文件共分为多少块，最新修改的一个date

以及一个线程池，以及一个CompletionService，

 在Manager类中采用concurrentHashMap<>() 进行维护

用md5码，作为key，BlockFile作为value。



设计了一个Callable进行 分块文件的写入

在写入时，通过打开文件的Channel，采用零拷贝技术transferform(read,position,size) 写入



判断是否是最后一个写入块，遍历所有status，判断是否所有的都写入成功。

写入成功返回一个dto,记录哪个分块，分块多大，成功或失败，记录写入数据库。

service 中 通过completionService 同步等待 最新执行完的future 返回给controller

```
private byte[] data;
private BlockFileParam blockFileParam;
```



定时任务每两个小时会删除废弃的缓存，根据修改事件进行判断





并发控制，

采用分段锁的方式，采用16个 段， 以及Object数据，对Md5进行hash，得到槽位，采用sychronized进行上锁。



### 分布式存储  Zookeeper + Dubbo

在网关层，利用Dubbo SPI 自定义Cluster 集群策略以及ClusterInvoker，在ClusterInvoker中过滤一遍Invoker，将容量不够的服务排除，再调用自定义的一致性Hash负载均衡策略，通过将获取参数中的文件Md5，作为key进行Hash，将同一个文件的块映射到同一个服务上去。

服务提供者通过向Zookeeper 注册服务以及本机磁盘容量信息，网关作为服务消费者 订阅Zookeeper上的服务节点，获取远程服务主机的磁盘容量，实现动态横向扩展。





## HTTP 3.0 2.0 1.1 1.0 QUIC





##  预约模块

网关



<img src="E:\秋招\isyslab_recruitment_tips\个人整理\cc\预约流程图 (1).png" alt="预约流程图 (1)"  />

> 1. 检验该医生是否存在，且医生处于工作状态
> 2. 检验医生是否存在排班
> 3. 检验请求时间是否合法
> 4. 判断是否存在该场次的预约
> 5. 检验该时段是否可预约人数是否大于0
> 6. 是否大于最大重试次数
> 7. redis 获取预约时段分布式锁
> 8. 获取失败 重试次数 +1 大于最大重试次数 返回失败
> 9. 启动一个线程，sleep1.5 s 进行一个续锁操作 每1.5秒续锁 重试次数+1
> 10. 创建预约为状态为初始
> 11. rpc 调用排班系统，扣减预约医生时段 剩余人数
> 12. 扣减成功 更新预约状态为预约成功
> 13. rocketmq 投递短信服务消息 预约成功
> 14. 异常，投递消息，topic ，更新失败，回退库存
> 15. 删除分布式锁
> 16. 返回 网关 预约结果

采用redis 分布式锁 进行并发扣减控制

set key value ex xxx nx

set key value px xx nx

```
redisTemplate.opsForValue().setIfAbsent(key,value,timeOut,TimeUnit.MILLISECONDS);
```

redis 存储 set 当天所有的医生信息，采用Json 字符串存储在redis中，

redis 存储 set 当天某个医生 所有场次信息,

采用 string 方式存储某个医生具体某个场次 预约的信息.

采用重试+setnx方式进行控制，重试次数设置为5，

每次获取之前和获取 当前场次的信息 判断是否大于0

获取到锁后，启动线程进行定时续锁，

修改成功以及数据库修改成功，则判断value是否一直，删除锁，以及future.cancel

分布式id生成





### 项目架构

采用微服务的架构

使用了dubbo开源框架 支持服务之间的远程调用

用户请求到达网关

网关作用主要 安全认证，限流熔断，日志监控 ，作为服务的消费者去调用业务层

Zookeeper 作为 注册中心，用于服务注册

业务层 将业务模块划分为各个服务，作为服务的提供者

业务层分为 

- 用户登录注册服务  **提供用户手机号注册，登录功能**
- 用户信息管理服务 **提供用户对个人信息修改的功能**
- 用户预约订单服务 **提供用户预约医生功能**     1
- 医生排班服务 **提供医生排班，以及预约扣减**    1
- 文件上传和下载服务 **提供管理人员上传文件以及视频观看 稳定的传输服务**   1
- 心理测评服务 **提供用户在线心理测评功能，生成试卷，生成测评结果**    1
- 处方库管理服务 **查询和修改处方**
- 短信服务 **使用阿里云的短信服务 提供短信验证码功能以及提示功能**   1



//

推送  + 考试 + 文件共享





持久层，使用了redis 和Mysql

redis 主要用户登录token的存储，短信验证码的存储，以及一些热点业务数据的缓存

Mysql 主要所有数据的持久层





