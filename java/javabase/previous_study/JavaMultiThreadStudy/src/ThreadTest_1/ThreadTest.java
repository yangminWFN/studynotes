package ThreadTest_1;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * 一、用synchronized修饰的方法
 * 1.在多个线程共同调用 !!同一!! synchronized方法时，同一时刻只有一个线程可以进入到该方法，并获得该实例对象的锁。
 * 直到方法执行完毕，才会释放该锁，其他线程才有机会进入该方法。
 * 2.在多个线程调用一个类的 !!不同!! 的synchronized方法时，同一时刻也只有一个线程可以获取到该实例对象的锁。
 * 即当一个线程访问一个同步方法时，其他线程就不能访问其他任何的同步方法了。
 * 3.在同一个类中用synchronized修饰的方法和不用synchronized方法，如果方法签名一样则不算是重载，会已经定义的错误
 * 4.父类中用synchronized修饰的方法在子类中不加synchronized重写是不具有同步性的。但是子类可以像一般继承一样继承到该方法
 * <p>
 * 二、用synchronized修饰的代码块
 * 1.synchronized(this){...},this在多个线程中属于共享（同一个）对象，同一时间只有一个线程能够进入该代码块，
 * 并且其他syschronized(this)代码块和syschronized方法都将被阻塞。
 * 2.synchronized(X){...}，其中X不是this，表示任意对象。如果在多个线程中共享对象X，那么在效果与this相同，不过是获得了X对象的锁。
 * 如果X为局部对象，不被多个线程共享，那么这个用synchronized修饰的代码块是异步的。
 * <p>
 * 三、用synchronized修饰类方法和类(AClass)
 * 1.修饰类方法表示获取这个类的锁，同一时间只有一个线程能够访问这个类的同步类方法或者syschronized(AClass.class){...}代码块
 * 2.synchronized(AClass.class){...}代码块也是获取这个类的锁
 * 3.问题：当一个线程已经获取到这个类的锁时，其他线程还能获取这个类的实例的锁吗？？？
 * 答案：可以的。类的锁并不是这个类所有实例对象的锁，只是AClass.class这个Class对象的锁
 */

class MyList {
    private ArrayList<Integer> list = new ArrayList<Integer>();

    public void add(int i) {
        list.add(i);
    }

    public int size() {
        System.out.println("我被调用了");
        return list.size();
    }
}

class ThreadA extends Thread {
    private MyList list;

    public ThreadA(String name, MyList list) {
        setName(name);
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            list.add(i);
            System.out.println("当前添加数字为：" + i);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ThreadB extends Thread {
    private MyList list;

    public ThreadB(String name, MyList list) {
        setName(name);
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
            if (list.size() == 5) {
                break;
            }

        }
        System.out.println("B线程退出了");
    }

}

public class ThreadTest {
    public static void main(String[] args) {
        MyList list = new MyList();
        ThreadA a = new ThreadA("A", list);
        ThreadB b = new ThreadB("B", list);
        a.start();
        b.start();

    }
}
