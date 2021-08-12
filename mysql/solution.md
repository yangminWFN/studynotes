# Mysql Solution

## 一、关键字limit

`limit` 是从0开始的，也就是说记录下标是从0开始索引的。

```sql
--返回从下标0开始的记录，共2条：记录0，记录1
select * from employee limit 2;

--返回从下标1开始的记录，共3条：记录1，记录2，记录3
select * from employee limit 1,3;
```



