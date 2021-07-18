package multi_thread;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;


public class UnSafeThread {
    private AtomicInteger cnt = new AtomicInteger();

    public void add() {
        cnt.incrementAndGet();
    }

    public int getCount() {
        return cnt.intValue();
    }

    public static void main(String[] args) {
        final int threadCount = 1000;
        UnSafeThread example = new UnSafeThread();
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < threadCount; i++) {
            executorService.execute(new Runnable() {

                @Override
                public void run() {
                    example.add();
                    countDownLatch.countDown();
                }

            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println(example.getCount());
        
    }
}
