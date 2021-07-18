package multi_thread;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class SynchronizedClass {
    public void fun1() {
        synchronized (this.getClass()){
            System.out.println("");
            for (int i = 0; i < 10; i++) {
                System.out.print(" " + i);
            }
            throw new IllegalArgumentException();
        }
    }
}

public class SynchronizedTest {
    public static void main(String[] args) {
        SynchronizedClass obj = new SynchronizedClass();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> obj.fun1());
        service.execute(() -> obj.fun1());
        service.shutdown();
    }

}
