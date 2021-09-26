package edu.wit.multi_thread.pc;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockPC {

    private static int count = 0;
    private static final int MAX_SIZE = 10;

    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition notFull = lock.newCondition();
    private static final Condition notEmpty = lock.newCondition();

    static class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {

                try {
                    Thread.sleep(new Random().nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                lock.lock();
                try {
                    while (count == MAX_SIZE) {
                        notFull.await();
                    }
                    count++;
                    System.out.println("生产了1个产品，当前产品数量为:" + count);
                    notEmpty.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {

                try {
                    Thread.sleep(new Random().nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                lock.lock();
                try {
                    while (count == 0) {
                        notEmpty.await();
                    }
                    count--;
                    System.out.println("消费了1个产品，当前产品数量为:" + count);
                    notFull.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
        }
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new SynPC.Producer()).start();
            new Thread(new SynPC.Consumer()).start();
        }
    }
}
