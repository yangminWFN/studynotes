package multi_thread;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

class MyReentrantLock {
    private ReentrantLock lock = new ReentrantLock();

    public void fun1() {
        lock.lock();
        try {
            System.out.println("");
            for (int i = 0; i < 10; i++) {
                System.out.print(" " + i);
            }
        } finally {
            lock.unlock();
        }
    }
}

public class ReetrantLockTest {

    public static void main(String[] args) {
        MyReentrantLock myReentrantLock = new MyReentrantLock();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> myReentrantLock.fun1());
        executorService.execute(() -> myReentrantLock.fun1());
        executorService.shutdown();     
    }
    
}
