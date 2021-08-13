package edu.wit.multi_thread.producer_consumer;

import java.util.Random;

public class PCByWaitNotify {
    // 产品数量
    private static int count;

    // 锁
    private static Object lock = new Object();

    // 缓冲区个数
    private static int SIZE = 10;

    static class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                // 每生产一个产品休息一下
                try {
                    Thread.sleep(new Random().nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock) {
                    // 使用while而不是if的原因：若生产者被notify唤醒后，会从lock.wait()处继续执行；此时，可能缓冲区依旧是满的，生产者需要再次阻塞。
                    while (count == SIZE) {
                        System.out.println("缓冲区满了" + Thread.currentThread().getName() + "被阻塞。");
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count++;
                    System.out.println(Thread.currentThread().getName() + "生产了1个产品，当前产品数量为：" + count);
                    lock.notifyAll();
                }
            }
            System.out.println(Thread.currentThread().getName() + "已完成10个产品的生产");
        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            // 每个消费者消费10个产品
            for (int i = 0; i < 10; i++) {
                // 每消费一个产品休息一下
                try {
                    Thread.sleep(new Random().nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock) {
                    while (count == 0) {
                        System.out.println("缓冲区是空的，" + Thread.currentThread().getName() + "被阻塞。");
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count--;
                    System.out.println(Thread.currentThread().getName() + "消费了1个产品，当前产品数量为：" + count);
                    lock.notifyAll();
                }
            }
            System.out.println(Thread.currentThread().getName() + "已完成10个产品的消费");
        }
    }

    /**
     * 原有的条件队列wait/notify太笼统，比如生产者生产完一个对象，notify/notifyAll的时候也会通知到在该锁上wait的其他生产者，但其实通知消费者就够了。
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
