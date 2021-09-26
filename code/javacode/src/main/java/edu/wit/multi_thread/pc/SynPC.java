package edu.wit.multi_thread.pc;

import java.util.Random;

public class SynPC {

    private static int count = 0;
    private static final Object lock = new Object();
    private static final int MAX_SIZE = 10;

    static class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {

                try {
                    Thread.sleep(new Random().nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock) {
                    while (count == MAX_SIZE) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count++;
                    System.out.println("生产了1个产品，当前产品数量为:" + count);
                    lock.notifyAll();
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

                synchronized (lock) {
                    while (count == 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count--;
                    System.out.println("消费了1个产品，当前产品数量为:" + count);
                    lock.notifyAll();
                }
            }

        }
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Producer()).start();
            new Thread(new Consumer()).start();
        }
    }
}
