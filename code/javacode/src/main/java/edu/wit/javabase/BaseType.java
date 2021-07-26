package edu.wit.javabase;

import java.util.ArrayList;

class Father {
    private static String name = "我是父类静态变量";
    static {
        System.out.println(name);
        System.out.println("我是父类的静态语句块");
    }

    Father() {
        System.out.println("我是父类的构造函数");
    }

    public void printFather() {
        System.out.println("我是爸爸");
    }

}

class Son extends Father {
    private static String name = "我是子类静态变量";
    static {
        System.out.println(name);
        System.out.println("我是子类的静态语句块");
    }

    Son() {
        System.out.println("我是子类的构造函数");
    }
}

public class BaseType {
    private static final String ArrayList = null;
    private String name = "yangmin";

    class InnerClass {
        public void print() {
            System.out.println(name);
        }
    }

    public static void main(String[] args) throws Exception {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1); // 这样调用 add 方法只能存储整形，因为泛型类型的实例为 Integer
        list.getClass().getMethod("add", Object.class).invoke(list, "asd");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        // 两种写法的不同
        // ArrayList<String> list1 = new ArrayList();
        // list1.add("e");
        // list1.add(1);

        // ArrayList list2 = new ArrayList<String>();
        // list2.add("m");
        // list2.add(1);

        System.out.println("nem" instanceof String);

        Son son = new Son();
        son.printFather();

    }
}
