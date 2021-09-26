package edu.wit.javaio.nio;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOTest {


    public static void main(String[] args) throws Exception {
        // 获取文件的channel
        RandomAccessFile accessFile = new RandomAccessFile("data\\nio\\test.txt","rw");
        FileChannel channel = accessFile.getChannel();

        //创建buffer
        ByteBuffer buffer = ByteBuffer.allocate(24);

        // 从文件中读取数据到buffer
        int readCount = channel.read(buffer);
        System.out.println(readCount);
        System.out.println(new String(buffer.array(),"UTF-8"));

        buffer.clear();
        readCount = channel.read(buffer);
        System.out.println(readCount);
        System.out.println(new String(buffer.array(),"UTF-8"));

        channel.close();
    }
}
