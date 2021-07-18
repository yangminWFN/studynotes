# Spring问题

==1、问题：在开启Spring事务管理后。druid连接池中出现日志警告==

```java
JDBC Connection [com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@6760dae] will be managed by Spring
```

解决方案：在Service上加上@Transactional注解

