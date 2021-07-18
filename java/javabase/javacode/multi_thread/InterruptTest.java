package multi_thread;
public class InterruptTest {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    System.out.println("线程开始睡眠");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread.interrupt();
        System.out.println("Main");

    }
}
