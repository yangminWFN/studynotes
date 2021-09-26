package edu.wit.javaio.nio;
import org.junit.jupiter.api.Test;
import sun.nio.ch.DirectBuffer;

import java.nio.Buffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;

public class MyBuffer {

    @Test
    public void bufferTest(){
        IntBuffer buffer = IntBuffer.allocate(8);
        // 写数据
        int[] a = new int[]{1,2,3,4,5,6};
        buffer.put(a);
        System.out.println(buffer.position());

        // 读数据
        buffer.flip();
        int t = buffer.get();
        System.out.println(t);
        System.out.println(buffer.position());
        int[] b = new int[5];
        buffer.get(b);
        System.out.println(buffer.position());
//        int i = buffer.get();
//        System.out.println(i);

    }
}
