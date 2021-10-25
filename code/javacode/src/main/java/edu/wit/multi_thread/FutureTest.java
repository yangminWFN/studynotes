package edu.wit.multi_thread;

import java.util.concurrent.*;

public class FutureTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Future<Integer> future = executorService.submit(() -> {
            try {
                System.out.println("异步任务正在执行。。。");
                Thread.sleep(3000);
                System.out.println("异步任务执行完毕。。。");
                return 1;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return 0;
            }
        });
        System.out.println("同步任务执行中。。。");
        Thread.sleep(1000);
        System.out.println("同步任务执行完毕。。。");
        try {
            final Integer res = future.get(3, TimeUnit.SECONDS);
            System.out.println("异步任务的结果是" + res);
        } catch (TimeoutException | ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
