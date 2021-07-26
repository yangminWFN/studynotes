package edu.wit.multi_thread;

import java.util.concurrent.Semaphore;

/**
 * 三个线程交替打印十次ABC
 * 结果：ABCABCABCABCABCABCABCABCABCABC
 */
public class TurnPrint {
    //定义三个信号量
    private static Semaphore A = new Semaphore(1);
    private static Semaphore B = new Semaphore(0);
    private static Semaphore C = new Semaphore(0);

    static class ThreadA extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
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
            for (int i = 0; i < 10; i++) {
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
            for (int i = 0; i < 10; i++) {
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
        new ThreadA().start();
        new ThreadB().start();
        new ThreadC().start();
    }
}
