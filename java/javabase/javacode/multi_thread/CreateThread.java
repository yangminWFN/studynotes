package multi_thread;
import java.util.HashSet;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Lock;

class MyThread extends Thread {
    private int[] a;

    public MyThread() {

    }

    public MyThread(int[] a) {
        this.a = a;
    }

    @Override
    public void run() {
        a[0]++;
    }

}

class MyRunnable implements Runnable {

    private int[] a;

    public MyRunnable() {
    }

    public MyRunnable(int[] a) {
        this.a = a;
    }

    @Override
    public void run() {
        a[0]++;
    }

}

class MyCallable implements Callable<Integer> {

    private int[] a;

    public MyCallable() {
    }

    public MyCallable(int[] a) {
        this.a = a;
    }

    @Override
    public Integer call() throws Exception {
        a[0]++;
        return a[0];
    }

}

public class CreateThread {
    public static void main(String[] args) throws Exception{
        int[] a = new int[] { 0 };
        // 第一种
        // for (int i = 0; i < 10000; i++) {
        // Thread thread = new MyThread(a);
        // thread.start();
        // }

        // 第二种
        // for (int i = 0; i < 1000; i++) {
        // MyRunnable myRunnable = new MyRunnable(a);
        // Thread thread = new Thread(myRunnable);
        // thread.start();
        // }

        // 第三种
        for (int i = 0; i < 20; i++) {
            MyCallable myCallable = new MyCallable(a);
            FutureTask<Integer> futureTask = new FutureTask<>(myCallable);
            Thread thread = new Thread(futureTask);
            thread.start();
            System.out.println(futureTask.get());
        }

        try {
            Thread.sleep(3000);
            // System.out.println(a[0]);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}