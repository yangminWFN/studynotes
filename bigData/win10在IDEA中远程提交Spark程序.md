# win10在IDEA中远程提交Spark程序

==注意点：Spark3.1.1对应的Scala版本必须是2.12.10，否则会产生错误；之前使用的Scala2.12.12就会产生错误。win10和linux环境下的scala版本都必须是2.12.10==

==win10电脑的防火墙要关闭！！！或者在防火墙中设置入站规则，将集群的IP允许访问==

Spark程序在IDEA中运行时，win10上的IDEA就相当于Driver端，如果不指定Driver的主机名、IP、端口的话，Spark程序会默认将win10的主机名(DESKTOP-OVTESQC)传递给集群中的Executor。集群中会由于DNS解析失败，导致无法与Driver端进行通信。

所以在IDEA中运行Spark程序时，必须显示指定Driver主机IP。指定端口号是为了防止Spark随机选择的端口号被其他程序占用。具体配置代码如下：

```java
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class JavaWordCount {
    private static final Pattern SPACE = Pattern.compile(" ");

    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf();
        sparkConf.setJars(new String[]{"D:\\Study\\ymstudy\\javasparkstudy\\target\\sparkStudy.jar"});
        sparkConf.set("spark.driver.host", "192.168.2.238");
        sparkConf.set("spark.driver.port", "4567");
        SparkSession spark = SparkSession
                .builder()
                .appName("JavaWordCount")
                .master("spark://192.168.2.151:7077")
                .config(sparkConf)
                .getOrCreate();

        JavaRDD<String> lines = spark.read().textFile("hdfs://192.168.2.151:9000/input").javaRDD();

        JavaRDD<String> words = lines.flatMap(s -> Arrays.asList(SPACE.split(s)).iterator());

        JavaPairRDD<String, Integer> ones = words.mapToPair(s -> new Tuple2<>(s, 1));

        JavaPairRDD<String, Integer> counts = ones.reduceByKey((i1, i2) -> i1 + i2);

        List<Tuple2<String, Integer>> output = counts.collect();
        for (Tuple2<?, ?> tuple : output) {
            System.out.println(tuple._1() + ": " + tuple._2());
        }
        spark.stop();
    }
}
```

指定Driver主机IP

`sparkConf.set("spark.driver.host", "192.168.2.238");`

指定Driver主机与Executor通信的端口号

`sparkConf.set("spark.driver.port", "4567");`

另外，在每次提交Spark程序时，还需要使用maven clean清除和maven package进行打包，然后指定打包好程序的jar包路径，`sparkConf.setJars(new String[{"D:\\Study\\ymstudy\\javasparkstudy\\target\\sparkStudy.jar"});`



## Scala编写的Spark程序

```scala
val sparkConf=new SparkConf().setMaster("spark://192.168.2.151:7077").setAppName("myTest")
sparkConf.set("spark.driver.host", "192.168.2.238")
sparkConf.set("spark.driver.port", "4567")
sparkConf.setJars(Array("D:\\Study\\ymstudy\\sparkstudy\\target\\sparkStudy.jar"))
val sc=new SparkContext(sparkConf)
```

加上上述代码就可以远程提交了

