package edu.wit.multi_thread;

public class TurnOddEven {
    private static int count = 0;

    static class TurnRunner implements Runnable {
        @Override
        public void run() {
            while (count <= 100) {
                synchronized (this) {
                    System.out.println(Thread.currentThread().getName() + ": " + count);
                    count++;
                    this.notifyAll();
                    try {
                        if (count <= 100) {
                            // 只有在任务没有完成前需要阻塞
                            this.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new TurnRunner();
        Thread odd = new Thread(r, "偶数线程");
        Thread even = new Thread(r, "奇数线程");
        odd.start();
        Thread.sleep(10);
        even.start();
    }
}
