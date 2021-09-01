# MySQL和Redis

## 一、MySQL

1、MySQL中的分页优化

```mysql
-- 从下标为1的行开始（包括在内）返回连续的3行；行下标计数从0开始！！！
select * from employee limit 1,3;
```

优化分页：



2、慢查询优化

在mysql中可以使用查看慢查询日志 `slow_query_log` 的方式的来查看有哪些sql语句执行得比较慢，可以设置一个阈值，执行时间超过这个阈值的话，就会被慢查询日志记录下来。

针对某一条单独的sql，可以使用 `explain` 关键字来查看当前查询语句一些信息。

- 查询类型（简单查询simple、关联查询或子查询primary）

- 访问单表的type（all全表扫描、ref基于索引的等值查询或连接、range使用索引进行范围查询、index全索引扫描）
- 额外信息extra（Using index使用了覆盖索引、Using where使用了where里的条件、Using index condition先按条件过滤再按索引查询数据）
- 具体是使用了那个索引key、可能的索引possible_key、实际使用到的索引长度key_len（联合索引时用得到）

## 二、Redis