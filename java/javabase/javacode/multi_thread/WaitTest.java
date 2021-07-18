package multi_thread;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class WaitObject {
    synchronized public void before() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("before");
        System.out.println("do something");
        notifyAll();
    }

    synchronized public void after() {
        try {
            // 使用wait时会释放当前的锁，然后将当前线程挂起
            wait();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("after"+new Random().nextInt());
    }
}

// 一个任务等多个任务完成后开始执行，可以使用join的方式
// 多个任务等待一个任务完成后，同时开始运行可以wait的方式
public class WaitTest {
    public static void main(String[] args) {
        WaitObject waitObject = new WaitObject();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> waitObject.after());
        service.execute(() -> waitObject.after());
        service.execute(() -> waitObject.after());
        service.execute(() -> waitObject.after());
        service.execute(() -> waitObject.after());
        service.execute(() -> waitObject.before());
        service.shutdown();
    }
}
