package design_mode;

/**
 * 单例模式：双重检验单例模式
 */
public class Singleton {
    private volatile static Singleton singleton;

    private Singleton() {
    }

    public static Singleton newInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

}

/**
 * 懒汉单例模式： 类加载时不创建实例，在第一个线程访问同步块时创建实例并返回，其他线程判断已经有了实例就直接取，每次需要访问同步块，会有性能问题
 */
class LazySingleton {
    private static LazySingleton singleton;

    private LazySingleton() {
    }

    public static synchronized LazySingleton newInstance() {
        if (singleton == null) {
            singleton = new LazySingleton();
        }
        return singleton;
    }
}

/**
 * 饿汉单例模式：在类一开始加载时生成一个实例对象，以后每次就取这个实例
 */
class HungrySingleton {
    private static final HungrySingleton singleton = new HungrySingleton();

    private HungrySingleton() {
    }

    public static HungrySingleton newInstance() {
        return singleton;
    }
}
