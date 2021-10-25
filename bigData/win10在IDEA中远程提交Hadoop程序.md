# win10在IDEA中远程提交Hadoop程序

本文演示如何在win10环境下在IDEA中实现远程提交WordCount程序，并且安装big data tool插件来实现hadoop集群环境监控和HDFS文件系统访问。

实验环境：

```markdown
java 1.8
hadoop 3.2.2
IDEA 2020.1
win10
hadoop.dll 3.0
winutil.exe
```

hadoop.dll要放入`C:\Windows\System32`目录和 `%HADOOP_HOME%\bin`目录中，==放在后者的目的是为了可以使用IDEA的big data tools来查看HDFS中的文件==

winutil.exe要放入 `%HADOOP_HOME%\bin`目录中，其中`%HADOOP_HOME%`是win10下hadoop的安装路径

新建一个Maven项目，在POM文件中导入hadoop相关依赖：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>edu.wit</groupId>
    <artifactId>hadoopstudy</artifactId>
    <version>1.0-SNAPSHOT</version>
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <java.version>1.8</java.version>
        <hadoop.version>3.2.2</hadoop.version>
        <maven.compiler.target>1.8</maven.compiler.target>
        <maven.compiler.source>1.8</maven.compiler.source>
    </properties>
    <repositories>
        <repository>
            <id>central</id>
            <name>Central Repository</name>
            <url>http://maven.aliyun.com/nexus/content/repositories/central</url>
            <layout>default</layout>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </repository>
    </repositories>

    <dependencies>
        <dependency>
            <groupId>org.apache.hadoop</groupId>
            <artifactId>hadoop-common</artifactId>
            <version>${hadoop.version}</version>
        </dependency>
        <dependency>
            <groupId>org.apache.hadoop</groupId>
            <artifactId>hadoop-mapreduce-client-core</artifactId>
            <version>${hadoop.version}</version>
        </dependency>
        <dependency>
            <groupId>org.apache.hadoop</groupId>
            <artifactId>hadoop-hdfs</artifactId>
            <version>${hadoop.version}</version>
        </dependency>
        <dependency>
            <groupId>org.apache.hadoop</groupId>
            <artifactId>hadoop-mapreduce-client-jobclient</artifactId>
            <version>${hadoop.version}</version>
        </dependency>
        <dependency>
            <groupId>org.apache.hadoop</groupId>
            <artifactId>hadoop-mapreduce-client-common</artifactId>
            <version>${hadoop.version}</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.1</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-assembly-plugin</artifactId>
                <version>3.3.0</version>
                <configuration>
                    <archive>
                        <manifest>
                            <addClasspath>true</addClasspath>
                            <classpathPrefix>lib/</classpathPrefix>

                            <mainClass>edu.wit.WordCount</mainClass>

                        </manifest>
                    </archive>
<!--                    <descriptorRefs>-->
<!--                        <descriptorRef>jar-with-dependencies</descriptorRef>-->
<!--                    </descriptorRefs>-->
                    <descriptorRefs>
                        <descriptorRef>project</descriptorRef>
                    </descriptorRefs>
                    <encoding>UTF-8</encoding>
                </configuration>
                <executions>
                    <execution>
                        <id>make-assembly</id>
                        <phase>package</phase>
                        <goals>
                            <goal>single</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>
```

1、在`/src/main/resource`目录中添加文件：core-site.xml、log4j.properties、mapred-site.xml、yarn-site.xml，这四个文件直接从服务器中拷贝过来即可，不用做修改。

2、新建一个WordCount类：

```java
package edu.wit;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;


public class WordCount {

    public static class TokenizerMapper
            extends Mapper<Object, Text, Text, IntWritable> {


        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();


        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            while (itr.hasMoreTokens()) {
                word.set(itr.nextToken());
                context.write(word, one);
            }
        }
    }


    public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        private IntWritable result = new IntWritable();


        public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }
            result.set(sum);
            context.write(key, result);
        }
    }


    public static void main(String[] args) throws Exception {
        // 设置运行的用户，否则会出现无权限错误
        System.setProperty("HADOOP_USER_NAME", "hadoop");
        System.setProperty("hadoop.home.dir", "D:\\Programming\\hadoop-3.2.2");
        Configuration conf = new Configuration();
        conf.set("fs.default.name", "hdfs://192.168.2.151:9000");
        // 设置为跨平台
        conf.set("mapreduce.app-submission.cross-platform", "true");
        // 设置为完全分布式任务提交到集群
        conf.set("mapreduce.framework.name", "yarn");
        // 指定当前项目打好的jar包，可以不带依赖，指定为project打包即可
        conf.set("mapred.jar", "D:\\Study\\ymstudy\\hadoopstudy\\target\\hadoopstudy-1.0-SNAPSHOT.jar");
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length < 2) {
            System.err.println("Usage: wordcount <in> [<in>...] <out>");
            System.exit(2);
        }
        Job job = Job.getInstance(conf, "word count");
        job.setJarByClass(WordCount.class);
        job.setMapperClass(TokenizerMapper.class);
        job.setCombinerClass(IntSumReducer.class);
        job.setReducerClass(IntSumReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        for (int i = 0; i < otherArgs.length - 1; ++i) {
            FileInputFormat.addInputPath(job, new Path(otherArgs[i]));
        }
        FileOutputFormat.setOutputPath(job,
                new Path(otherArgs[otherArgs.length - 1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
```

每次在修改代码后，可以先使用maven clean来对构建的jar进行清除，然后再使用maven package来进行打包，如果不需要额外的jar包，那么可以只需要使用project打包方式即可，不用额外的依赖。

