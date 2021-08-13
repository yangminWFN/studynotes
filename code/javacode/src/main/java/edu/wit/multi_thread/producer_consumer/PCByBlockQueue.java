package edu.wit.multi_thread.producer_consumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PCByBlockQueue {

    // 产品数量
    private static int count;

    // 缓冲区大小
    private static final int SIZE = 10;

    // 阻塞队列
    private static final BlockingQueue<Integer> BLOCKING_QUEUE = new ArrayBlockingQueue<>(SIZE);

    static class Producer implements Runnable{
        @Override
        public void run() {
            // 每生产1个产品休息一下
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(new Random().nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    BLOCKING_QUEUE.put(1);
                    count++;
                    System.out.println(Thread.currentThread().getName() + "生产了1个产品，当前产品数量为：" + count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    static class Consumer implements Runnable{
        @Override
        public void run() {
            // 每消费1个产品休息一下
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(new Random().nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    BLOCKING_QUEUE.take();
                    count--;
                    System.out.println(Thread.currentThread().getName() + "消费了1个产品，当前产品数量为：" + count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            Thread p = new Thread(new Producer());
            p.setName("生产者Thread-" + i);
            p.start();
            Thread c = new Thread(new Consumer());
            c.setName("消费者Thread-" + i);
            c.start();
        }
    }
}
