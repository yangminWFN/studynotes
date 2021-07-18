import javafx.util.Pair;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

class Apple implements Look {
    public int weight;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public void print() {
        System.out.println("实现了look接口");
    }
}

class User {
    private String name;
    public int age;

    public User() {
    }

    public User(String n) {
        this.name = n;
    }

    private User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "name:" + name + ",age:" + age;
    }

    private void showInfo()
    {
        System.out.println("this class's name is User");
    }
}

interface Look {
    void print();
}

class Shape{
    public void draw(String name){
        System.out.println("Shape is Drawing "+name);
    }
}

class Circle extends Shape{
    public String shapeToCircle(int i,String m)
    {
        return i+m;
    }

    public Circle()
    {
        super();
    }

    private void privateMethodOfCircle(String name)
    {
        System.out.println("privateMethodOfCircle:"+name);
    }

    public int getInt()
    {
        return 10;
    }
}

class ReflectCreateArray{
    public ReflectCreateArray(){}

    public static <T> void min(T[] a)
    {
//       错误
//       T[] b=new T[];
//        创建泛型数组的正确方法：使用反射机制来创建泛型数组
        T[] b=(T[])Array.newInstance( a.getClass().getComponentType(),a.length);

    }
}

public class ReflectTest implements Serializable {
    public static void main(String[] args) throws Exception {
//        //第一种创建Class对象的方式：调用类的静态属性class。使用该方式获得Class对象时不会动态加载该类。
//        //但是下面两种方式会动态加载该类
//        Class appleClass_1=Apple.class;
//
//        //第二中创建Class对象的方式：调用对象的getClass()方法
//        Apple apple=new Apple();
//        Class appleClass_2=apple.getClass();
//
//        //第三中创建Class对象的方式：调用Class类的静态方法forName
//        Class appleClass_3=Class.forName("Apple");
//
//        System.out.println("appleClass_1:"+appleClass_1.toString());
//        System.out.println("appleClass_2:"+appleClass_2.toString());
//        System.out.println("appleClass_3:"+appleClass_3.toString());
//
//        System.out.println("基本类型int的Class对象的toString:"+int.class.toString());
//
//        Look look=new Apple();
//        Apple lookApple=(Apple)look;
//
//        Class<Apple> appleClass=Apple.class;
//        Apple lookApple_2=appleClass.cast(look);
//
//        Class<? extends Number> numberClass=Integer.class;
//        numberClass=double.class;
//        numberClass=Number.class;
//        System.out.println( appleClass.isInstance(lookApple_2));
//        System.out.println(look instanceof Apple);

//        Class<?> classes=null;
//        classes=Class.forName("User");
//        User user1=(User)classes.newInstance();
//        user1.setName("yangmin");
//        user1.setAge(10);
//        System.out.println(user1.toString());
//        System.out.println("--------------------------------------------");
//
//        Constructor stringConstructor=classes.getConstructor(String.class);
//        User user2=(User)stringConstructor.newInstance("chenyu");
//        user2.setAge(100);
//        System.out.println(user2.toString());
//
//        //getConstructor()只能获取到public的构造方法，但是getDeclaredConstructor()可以获取public和private的构造方法
//        Constructor stringIntConstructor=classes.getDeclaredConstructor(String.class,int.class);
//        stringIntConstructor.setAccessible(true);
//        User user3=(User)stringIntConstructor.newInstance("zhongguanhua",200);
//        System.out.println(user3.toString());
//
//        Constructor<?>[] constructors=classes.getDeclaredConstructors();
//        for(Constructor<?> constructor:constructors)
//        {
//            Class<?>[] argClasses=constructor.getParameterTypes();
//            StringBuilder argsNames=new StringBuilder();
//            for(Class<?> currentClass:argClasses)
//            {
//                argsNames.append(currentClass.getName()+" ");
//            }
//            System.out.println("构造函数："+constructor.toString()+",参数列表为："+argsNames);
//        }
//        Class<?> userClass = User.class;
//        Field nameField = userClass.getDeclaredField("name");
//        Field ageField = userClass.getDeclaredField("age");
//        Field[] publicFields = userClass.getFields();
//        Field[] allFields = userClass.getDeclaredFields();
//        System.out.println("nameField:" + nameField.toGenericString());
//        System.out.println("ageField:" + ageField.toString());
//        for (Field f : publicFields) {
//            System.out.println("publicFields:"+f.toGenericString());
//        }
//        for(Field f:allFields)
//        {
//            System.out.println("allFields:"+f.toGenericString());
//        }
//        System.out.println(nameField.getDeclaringClass().getName());
//        User user=(User)(userClass.getConstructor().newInstance());
//        nameField.setAccessible(true);
//        nameField.set(user,"yangmin");
//        ageField.set(user,1000);
//        System.out.println(user);
//        System.out.println(nameField.get(user));
//        System.out.println(ageField.get(user));
//        System.out.println(nameField.getType().getName());
//        Class<?> circleClass=Class.forName("Circle");
//        Circle circle=(Circle) circleClass.getConstructor().newInstance();
////        Circle circle=new Circle();
//        Method[] allMethods=circleClass.getDeclaredMethods();
//        for(Method m:allMethods)
//        {
//            System.out.println(m.toGenericString());
//        }
//
//        Method privateMethod=circleClass.getDeclaredMethod("privateMethodOfCircle", String.class);
//        Method publicMethod=circleClass.getDeclaredMethod("shapeToCircle", int.class, String.class);
//        privateMethod.setAccessible(true);
//        privateMethod.invoke(circle,"yangmin");
//        System.out.println(publicMethod.invoke(circle,1000,"yangminya"));
//        Method method3=circleClass.getDeclaredMethod("getInt");
//        System.out.println(method3.getReturnType());
//        int[] a={1,2,3,4,5,6,7,8,9};
//        Class<?> arrClass=a.getClass();
//        System.out.println(arrClass.getComponentType());
//        arrClass=arrClass.getComponentType();
//        Object newArr= Array.newInstance(arrClass,15);
//        System.arraycopy(a,0,newArr,0,Array.getLength(a));
//        for(int i:(int[])newArr)
//        {
//            System.out.print(i+" ");
//        }
//        System.out.println(Array.getInt(newArr,8));
//        Array.set(newArr,9,1000);
//        System.out.println(Array.get(newArr,9));
////        可以通过反射来创建泛型数组
//        ArrayList<Pair<String,Object>> list=new ArrayList<Pair<String,Object>>();
//        Class<Integer> integerClass=int.class;
//        Integer i=integerClass.newInstance();
    }
}
