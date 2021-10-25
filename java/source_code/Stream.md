# Java8 Stream源码流程

```java
List<String> strs = new ArrayList<>(Arrays.asList("Abb", "caa","Alibaba", "Baidu"));
List<Integer> words = strs.stream()
                         .filter(x -> x.startsWith("A"))
    					 .map(x -> x.length())
    					 .sorted(x->x)
                         .collect(Collectors.toList());
```

Stream的操作可以分为中间操作和结束操作，只有在最后的结束操作执行后，所有中间操作才会执行，这个计算过程是惰性的。中间操作又可以分为有状态的(stateful)和无状态的(stateless)；有状态操作指的是当前元素的处理受前面元素的影响，必须等待所有元素都处理完之后才能知道结果。无状态操作指的是当前元素的处理不受前面元素的影响。例如，上面的filter和map是无状态操作，而sorted操作是有状态操作。

结束操作又分为短路操作和非短路操作，短路操作可以不用处理完所有元素久返回结果，非短路操作必须要处理完所有元素。例如，`collect(Collectors.toList())`就是非短路操作，它需要收集所有的元素；而findFirst()、findAny()等就是短路操作。

[java8Stream原理深度解析 - Dorae - 博客园 (cnblogs.com)](https://www.cnblogs.com/Dorae/p/7779246.html)