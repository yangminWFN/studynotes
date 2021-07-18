## 一、RDD的两种创建方式
1. 使用makeRDD([seq],numPatitions)，其中numPartitions为指定的分区个数，如不指定则为**集群中所有Executor的核心数之和**
    ```
    sc.makeRDD(List(1,2,3,4),5)   //指定当前RDD有5个分区
    ```
2. 使用sc.textFile(path,minNumPartitions),其中minNumPartitions为最小的分区数，如果不指定则为总的核心数与2中更小的一个；即使指定了该参数，**实际分区数也将大于或等于它，这与hadoop中文件的分片规则有关系**
    ```
    sc.textFile("file:///C:/input")  //第一次读取文件后，会有缓存，第二次再次由该RDD转换得到的RDD会快很多
    ```
***
## 二、RDD算子
1. **map,mapPartitions,mapPartitionsWithIndex**均是将当前RDD的各个分区的数据进行转化，转化后的数据还会在该分区。**区别在于**
    * map算子是对每一条数据进行一次计算，参数为每条数据，返回一条数据
    * mapPartitions算子是对每个分区的数据进行计算，参数为分区中数据集合，返回一个集合（**在内存空间比较大时，使用该方法效率高**）
    * mapPartitionsWithIndex算子与mapPartitions算子一样，不过参数中多了一个当前分区的**下标号**
    ```
    val listRDD = sc.makeRDD(List(1,2,3,4,5))
    val mapRDD = listRDD.map(x=>x*2)  //2，4，6，8，10
    val mapPartitionsRDD = listRDD.mapPartitions(data=>data.map(x=>x*3))  //3，6，9，12，15
    val mapPartitionsWithIndexRDD=listRDD.mapPartitionsWithIndex((index,data)=>data.map(x=>(index,x))) 
    ```
2. **flatMap**算子将数据进行压平处理,由当前元素转化为一个集合，然后将所有集合压平为一个个元素
    ```
    val listRDD = sc.makeRDD(List(List(1,2,3),List(4,5,6)))
    val flatMapRDD = listRDD.flatMap(x=>x)  //1,2,3,4,5,6
    ```
3. **glom**算子将一个分区内的数据转化为一个数组
    ```
    val listRDD = sc.makeRDD(List(1,2,3,4,5,6,7,8),2)  //两个分区
    val glomRDD = listRDD.glom()  //[1,2,3,4] [5,6,7,8]
    ```
4. **groupBy**算子将按照给定的分组函数对数据进行分组，具有**shuffle**操作，所以会执行得比较慢
    ```
    val listRDD = sc.makeRDD(List(1,2,3,4,5,6,7,8,9))
    val groupByRDD = listRDD.groupBy(x=>x%2) //(1,Buffer(1,3,5,7,9)) (0,Buffer(2,4,6,8))
    ```
5. **distinct**算子将集合进行去重处理。会将原有的分区打乱，具有shuffle操作
    ```
    val listRDD = sc.makeRDD(List(1,2,3,2,1,5,4,3,4),4)  //4分区
    val distinctRDD = listRDD.distinct(2)  //2分区
    ```
