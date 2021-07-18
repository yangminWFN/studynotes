package multi_thread;
class InterruptThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"开始了");
        while (!interrupted()) {

        }
        System.out.println(Thread.currentThread().getName()+"结束了");
    }
}

public class InterruptTest2 {
    public static void main(String[] args) throws InterruptedException{
        InterruptThread thread = new InterruptThread();
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }
    
     
}
