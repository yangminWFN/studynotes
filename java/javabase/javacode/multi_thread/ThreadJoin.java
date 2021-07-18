package multi_thread;
class AThread extends Thread {
    private String name;

    public AThread() {
    }

    public AThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + "开始执行啦");
        // System.out.println(Thread.currentThread().getName()+"开始执行啦");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // System.out.println(Thread.currentThread().getName()+"完成执行啦");
        System.out.println(name + "结束执行啦");
    }
}

public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        AThread[] theads = new AThread[10];
        for (int i = 0; i < 10; i++) {
            AThread t = new AThread("线程" + i);
            theads[i] = t;
            t.start();
        }
        for (int i = 0; i < 10; i++) {
            //这里的每个线程的join方法是依次执行的，看到各个线程交替完成是因为
            //当调用某个线程的join方法时，主线程会被阻塞，但其他线程没有被阻塞，依旧可以执行，可以先于当前线程执行完
            //只不过其他线程执行完后，也不能继续向下运行，因为此时主线程还是阻塞的，直到join方法按次序返回后，main线程后面才可以执行
            theads[i].join();
        }

        // 等待调用athread线程执行完之后才会执行main线程后面的语句，挂起当前的main线程
        System.out.println("main 线程已经等待所有的线程完成了");
    }
    //使用join的方式可以实现一个线程等待多个线程完成后，开始完成任务

}
