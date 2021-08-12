package edu.wit.javabase;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Objects;

class B{
    private static int num = 100;
    static{
        System.out.println("静态代码块");
        System.out.println(num);
        num = 300;
        System.out.println(num);
    }

}

public class ReflectTest {

    static class User {
        private String name;
        public int age;

        public void say(String str) {
            System.out.println(str);
        }

        private int add(int a, int b) {
            return a + b;
        }

        int haha() {
            return 1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return age == user.age &&
                    Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }
    }

    /**
     * 四种获取 Class 对象的方式
     */
    public static void getClassObject() {
        //第一种：使用 类名.class 获取
        Class<?> clazz1 = User.class;
        System.out.println("第一种：通过 类名.class 获取 Class对象：" + clazz1);

        //第二种：使用 对象.getClass() 获取
        User user = new User();
        Class<?> clazz2 = user.getClass();
        System.out.println("第二种：通过 对象.getClass() 获取 Class对象：" + clazz2);

        //第三种：使用 Class.forName("全类名")静态方法 获取，默认会进行类的初始化
        try {
            Class<?> clazz3 = Class.forName("edu.wit.javabase.ReflectTest$User");
            System.out.println("第三种：通过 Class.forName(‘全类名’) 获取 Class对象：" + clazz3);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //第四种：使用ClassLoader对象的 loadClass("全类名")方法来获取，默认不会进行类的初始化
        try {
            Class<?> clazz4 = ClassLoader.getSystemClassLoader().loadClass("edu.wit.javabase.ReflectTest$User");
            System.out.println("第四种：使用ClassLoader对象的 loadClass(‘全类名’)方法来获取 Class对象：" + clazz4);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        //加Declared的是所有本类中的，不加Declared是本类和父类中public的
        Class<?> clazz = User.class;
        User user = (User) clazz.getDeclaredConstructor().newInstance();
        Method addMethod = clazz.getDeclaredMethod("add", int.class, int.class);
        addMethod.setAccessible(true);
        Type returnType = addMethod.getGenericReturnType();
        System.out.println(returnType);
        System.out.println(addMethod.invoke(user, 1, 2));

        //测试四种获取Class对象的方式
        getClassObject();
        //在进行类加载时，为了避免在多线程下重复加载某个类，会使用一个并发map来记录正在加载的类的全类名作为key，Object对象作为value。
        //这个Object对象被作为加载这个类的锁，使用synchronized修饰来实现多线程同步
        new B();

    }
}
