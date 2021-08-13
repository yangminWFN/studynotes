package edu.wit.multi_thread.producer_consumer;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class PCBySemaphore {
    // 产品个数
    private static int count;

    //缓冲区大小
    private static final int SIZE = 10;

    // 空缓冲区信号量
    private static final Semaphore EMPTY = new Semaphore(SIZE);

    // 满缓冲区信号量
    private static final Semaphore FULL = new Semaphore(0);

    // 临界区互斥量
    private static final Semaphore MUTEX = new Semaphore(1);

    static class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                // 每生产1个产品休息一会
                try {
                    Thread.sleep(new Random().nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    EMPTY.acquire();
                    MUTEX.acquire();
                    count++;
                    System.out.println(Thread.currentThread().getName() + "生产了1个产品，当前产品数量为：" + count);
                    MUTEX.release();
                    FULL.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "已完成10个产品的生产");
        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                // 每消费1个产品休息一会
                try {
                    Thread.sleep(new Random().nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    FULL.acquire();
                    MUTEX.acquire();
                    count--;
                    System.out.println(Thread.currentThread().getName() + "消费了1个产品，当前产品数量为：" + count);
                    MUTEX.release();
                    EMPTY.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "已完成10个产品的消费");
        }
    }


    /**
     * 信号量的方式实现生产者消费者：（操作系统教科书方式的Java实现版）
     */
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
