package edu.wit.multi_thread;

public class WaitNotifyTest {

    /**
     * wait()是要释放对象锁，进入等待池。
     * 既然是释放对象锁，那么肯定是先要获得锁。
     * 所以wait必须要写在synchronized代码块中，否则会报异常。
     *
     * notify方法
     * 也需要写在synchronized代码块中,
     * 调用对象的这两个方法也需要先获得该对象的锁.
     * notify,notifyAll, 唤醒等待该对象同步锁的线程,并放入该对象的锁池中.
     * 对象的锁池中线程可以去竞争得到对象锁,然后开始执行.
     *
     * notify()或者notifyAll()调用时并不会真正释放对象锁, 必须等到synchronized方法或者语法块执行完才真正释放锁.
     *
     * @param args
     */
    public static void main(String[] args) {
        final Object lock = new Object();

        new Thread(()->{
            System.out.println("线程A等待获取对象的锁");
            synchronized (lock){
                System.out.println("线程A获取到对象的锁了");
                try {
                    Thread.sleep(100);
                    System.out.println("线程A开始等待了");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程A等待状态结束");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程B等待获取对象的锁");
                synchronized (lock){
                    System.out.println("线程B获取到对象的锁了");
                    try {
                        Thread.sleep(100);
                        System.out.println("线程B唤醒这个对象上所有等待的线程");
                        lock.notifyAll();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println("线程B唤醒结束");
                }
            }
        }).start();



    }
}
