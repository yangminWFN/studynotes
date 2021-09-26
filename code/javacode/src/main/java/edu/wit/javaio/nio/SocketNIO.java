package edu.wit.javaio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

public class SocketNIO {
    public static void main(String[] args) throws IOException {
        LinkedList<SocketChannel> clients = new LinkedList<>();

        ServerSocketChannel ss = ServerSocketChannel.open();
        ss.bind(new InetSocketAddress(9090));
        ss.configureBlocking(false);

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            SocketChannel client = ss.accept();
            if (client == null) {
                System.out.println("no client connected。。。");
            } else {
                client.configureBlocking(false);
                int port = client.socket().getPort();
                System.out.println("Current ClientPort:" + port);
                clients.add(client);
            }

            ByteBuffer buffer = ByteBuffer.allocate(4096);
            for (SocketChannel sc : clients) {
                int num = sc.read(buffer);
                if (num > 0) {
                    buffer.flip();
                    byte[] data = new byte[buffer.limit()];
                    buffer.get(data);
                    String str = new String(data);
                    System.out.println("Client Port:" + sc.socket().getPort() + ":" + str);
                    buffer.clear();
                }
            }

        }

    }
}
