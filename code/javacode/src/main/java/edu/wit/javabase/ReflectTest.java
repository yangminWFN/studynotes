package edu.wit.javabase;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

class User{
    private String name;
    public int age;
    public void say(String str){
        System.out.println(str);
    }
    private int add(int a,int b){
        return a+b;
    }

    int haha(){
        return 1;
    }
}

public class ReflectTest {
    public static void main(String[] args) throws Exception{
        Class<?> clazz = User.class;
        User user = (User)clazz.getDeclaredConstructor().newInstance();
        Method addMethod =  clazz.getDeclaredMethod("add",int.class,int.class);
        addMethod.setAccessible(true);
        Type returnType = addMethod.getGenericReturnType();
        System.out.println(returnType);
        System.out.println(addMethod.invoke(user, 1,2)); 
        
    }
}
