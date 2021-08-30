package edu.wit.multi_thread;

import java.util.concurrent.Semaphore;

/**
 * 三个线程交替打印十次ABC
 * 结果：ABCABCABCABCABCABCABCABCABCABC
 */
public class TurnPrint {
    //定义三个信号量
    private static final Semaphore A = new Semaphore(1);
    private static final Semaphore B = new Semaphore(0);
    private static final Semaphore C = new Semaphore(0);

    private static final int PRINTCOUNT = 100;

    static class ThreadA extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < PRINTCOUNT; i++) {
                try {
                    A.acquire();
                    System.out.print("A");
                    B.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < PRINTCOUNT; i++) {
                try {
                    B.acquire();
                    System.out.print("B");
                    C.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class ThreadC extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < PRINTCOUNT; i++) {
                try {
                    C.acquire();
                    System.out.print("C");
                    A.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        // new ThreadA().start();
        // new ThreadB().start();
        // new ThreadC().start();

        System.out.println(Math.pow(2, 14));
    }
}
