package edu.wit.disign_mode;

/**
 * 懒汉单例模式
 */
class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {
    }

    public synchronized static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}

/**
 * 饿汉单例模式
 */
class HungrySingleton {
    private static final HungrySingleton instance = new HungrySingleton();

    private HungrySingleton() {
    }

    public static HungrySingleton getInstance() {
        return instance;
    }
}

/**
 * 双重检验单例模式（懒汉）
 */
class DCSingleton {
    // volatile就是为了防止指令重排序，使得线程使用了未初始化的对象
    private static volatile DCSingleton instance;

    private DCSingleton() {
    }

    public static DCSingleton getInstance() {
        if (instance == null) {
            synchronized (DCSingleton.class) {
                if (instance == null) {
                    instance = new DCSingleton();
                }
            }
        }
        return instance;
    }
}

public class SingletonTest {

    public static void main(String[] args) {

    }
}
