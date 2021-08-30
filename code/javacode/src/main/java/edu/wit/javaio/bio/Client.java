package edu.wit.javaio.bio;

import java.net.Socket;
import java.util.Date;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(2000);
            new Thread(() -> {
                try {
                    Socket socket = new Socket("127.0.0.1", 3333);
                    try {
                        socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"客户端连接已断开");
            }).start();
        }
    }
}
