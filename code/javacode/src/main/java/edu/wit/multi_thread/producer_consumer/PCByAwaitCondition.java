package edu.wit.multi_thread.producer_consumer;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PCByAwaitCondition {

    // 产品个数
    private static int count;

    // 缓冲区个数
    private static final int SIZE = 10;

    // 锁
    private static final ReentrantLock lock = new ReentrantLock();

    // 缓冲区满条件变量
    private static final Condition notFull = lock.newCondition();

    // 缓冲区空条件变量
    private static final Condition notEmpty = lock.newCondition();

    static class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                // 每消费一个产品休息一下
                try {
                    Thread.sleep(new Random().nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                lock.lock();
                try {
                    while (count == SIZE) {
                        System.out.println("缓冲区满了" + Thread.currentThread().getName() + "被阻塞。");
                        notFull.await();
                    }
                    count++;
                    System.out.println(Thread.currentThread().getName() + "生产了1个产品，当前产品数量为：" + count);
                    notEmpty.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
            System.out.println(Thread.currentThread().getName() + "已完成10个产品的生产");
        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                // 每消费一个产品休息一下
                try {
                    Thread.sleep(new Random().nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                lock.lock();
                try {
                    while (count == 0) {
                        System.out.println("缓冲区是空的，" + Thread.currentThread().getName() + "被阻塞。");
                        notEmpty.await();
                    }
                    count--;
                    System.out.println(Thread.currentThread().getName() + "消费了1个产品，当前产品数量为：" + count);
                    notFull.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
            System.out.println(Thread.currentThread().getName() + "已完成10个产品的消费");
        }
    }

    /**
     * 使用Condition的await-signalAll方式实现生产者消费者模式
     * 使用两个Condition分别管理两个等待队列，每次生产者生产完成后就通知消费者消费，消费者消费完成后就通知生产者生产
     * 效率比wait-notify要高，避免了不必要的通知
     * 对于生产者来说，当队列满了，没有空间的时候，notFull条件不满足，那就调用await方法，
     * 使当前线程等待。如果有空间，那么生产者放一个元素进去，notEmpty条件满足，调用notEmpty条件的signal/signalAll，仅唤醒等待消费的消费者线程（因为只有消费者线程在notEmpty这个频道等着）。
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
