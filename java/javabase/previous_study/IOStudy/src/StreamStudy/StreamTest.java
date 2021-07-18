package StreamStudy;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


/**
 * 1.Java中File对象包含了给定路径下的文件和目录的基本信息，以及可以判断其是否存在。
 * 2.如果给定路径上的File对象的文件或者路径不存在，则可以创建文件和目录。也可以删除文件和目录。
 * 3.在删除指定的目录时，如果该目录下有文件，那么将删除失败。如果没有文件则可以像删除文件一样删除该目录。
 * 4.在FileInputStream中不带参数的read()方法读取一个字节，并返回这个这个字节的值（1个字节占8位，每一个字符对应一个ascii码值，即字节值）
 * 5.比如读取文件中字符'1'占一个字节，返回的字节值为49，是'1'的asci值
 * 6.在FileInputStream中带参数的read(byte[] b,int offset,int len)是读取len个字节数据到b中，返回实际读取的字节数
 * 7.new String(byte[] b,int offset,int len)可以将一个字节数组转化为字符串
 * 8.文本文件的换行占4个字节，最后一行要分情况，如果有回车符则有4个字节，如果没有回车符则只有两个字节
 * 9.在InputStream中的mark方法会标记当前输入流的指针位置，然后使用reset可以回到该指针位置。（只有支持mark方法的流可以，如BufferedInputStream）
 * 10.在BufferedInputStream中的mark(int limit)，其中的limit表示mark失效前可以读取的最大字节数。但实际与缓冲区大小有关。
 * 但实际的mark失效规则为：(1).如果 limit<=bufferSize,则limit无任何作用。在标记后继续读取bufferSize个字节后，mark才会失效
 * (2).如果limit>bufferSize,则limit参数才有效。在标记后继续读取limit个字节后，mark才会失效
 * 11.在使用输出流的write方法时，如果使用write(byte[] b)则会尽量把b中所有的字节全部读出，可能会有上一次输入流中的残留数据，导致错误。
 *      所以最好使用write(byte[] b,int offset,int len)这样来写入数据才比较保险。
 * 12.使用缓冲区来复制一个文件，使用了277毫秒，但是未使用缓冲区时使用了1276毫秒；
 * 13.缓冲区大小可以取81920个字节，而读取字节数组可以取8192个字节。可以达到很快的速度
 * 14.在使用ObjectOutputStream包装FileOutputStream后，会自动在该文件中写入4个字节的StreamHeader。
 *      对应的在使用ObjectInputStream包装FileInputStream时会先读取这4个字节的头部，如果没有找到，那么将会报EOFException
 *      所以在使用对象字节流时，必须先包装ObjectOutputStream然后再包装ObjectInputStream
 * 15.在存储对象的文件中反序列化对象，判断是否到达结尾可以使用try catch语句，如果捕获到EOFException则表示到达文件尾部
 */

class Wife implements Serializable{
    public String certificateName;
    public Wife(String s)
    {
        certificateName=s;
    }

    @Override
    public String toString() {
        return "结婚证书为："+certificateName;
    }


}

class Person implements Serializable,Cloneable{
    public String name;
    public int age;
    public Wife wife;

    public Person(String n,int a,String cer)
    {
        name=n;
        age=a;
        wife=new Wife(cer);
    }

    @Override
    public String toString() {
        return "name:"+name+",age:"+age;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

public class StreamTest {
    public static void main(String[] args) throws CloneNotSupportedException,IOException,ClassNotFoundException{
        Person p1=new Person("yangmin",19,"2019年8月10日结婚证");
//        Person p2=(Person)p1.clone();
//        p1.name="chenyu";
//        System.out.println("p1Name:"+p1.name);
//        System.out.println("p2Name:"+p2.name);
//        p1.wife.certificateName="2020年8月10日结婚证书";
//        System.out.println("p1Wife:"+p1.wife.certificateName);
//        System.out.println("p2Wife:"+p2.wife.certificateName);
        ByteArrayOutputStream bout=new ByteArrayOutputStream();
        ObjectOutputStream oout=new ObjectOutputStream(bout);
        oout.writeObject(p1);
        oout.close();
        ByteArrayInputStream bin=new ByteArrayInputStream(bout.toByteArray());
        ObjectInputStream oin=new ObjectInputStream(bin);
        Person p2=(Person)oin.readObject();
        p1.wife.certificateName="哈哈";
        System.out.println("p2Wife:"+p2.wife.certificateName);
        oin.close();

    }
}
