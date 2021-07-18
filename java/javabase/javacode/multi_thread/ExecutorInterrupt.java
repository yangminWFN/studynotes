package multi_thread;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorInterrupt {
    // public static void main(String[] args) {
    //     ExecutorService service = Executors.newCachedThreadPool();
    //     for (int i = 0; i < 5; i++) {
    //         service.execute(new Runnable() {

    //             @Override
    //             public void run() {
    //                 try {
    //                     // Thread.sleep(2000);
    //                 } catch (Exception e) {
    //                     e.printStackTrace();
    //                 }
    //                 System.out.println(Thread.currentThread().getName());
    //             }

    //         });
    //     }
    //     service.shutdownNow();
    //     System.out.println(Thread.currentThread().getName());
    // }

    public static void main(String[] args) throws Exception{
        ExecutorService eService = Executors.newCachedThreadPool();
        Future<?> future = eService.submit(() -> {
            try {
                System.out.println("start");
            } catch (Exception e) {
               e.printStackTrace();
            }
        
        });
        System.out.println("main start");
        Thread.sleep(1000);;
        
        System.out.println("main end");
        System.out.println(future.isDone());
        eService.shutdown();
    }
}
