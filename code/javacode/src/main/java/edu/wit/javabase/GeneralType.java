
package edu.wit.javabase;

import java.lang.reflect.Array;
import java.util.Arrays;

//泛型类
class Pair<T>{
    private T value;
    public T getValue(){
        return value;
    }
    public void setValue(T value){
        this.value = value;
    }
}

//继承特定类型的泛型类，实现多态
class MyClass extends Pair<String>{
    @Override
    public String getValue(){
        return super.getValue();
    }

    @Override
    public void setValue(String value){
        super.setValue(value);
    }
}


public class GeneralType {
    public static <S,T extends Number> double add(S a,T b){
        return ((Number)a).doubleValue()+((Number)b).doubleValue();
    }

    @SuppressWarnings({ "unchecked", "hiding" })
	public static <T>  T[] getArray(Class<T> componentType,int length) {
		return (T[]) Array.newInstance(componentType, length);
	}

    public static void main(String[] args) {
        // MyClass myClass = new MyClass();
        // myClass.setValue("yangmin");
        //会产生编译错误，证明不是因为类型擦除后，子类方法只是重载了，子类方法仍然是重写
        // myClass.setValue(new Object());
        System.out.println(add(1,1.5));

        Double[] d = getArray(Double.class, 10);
        System.out.println(Arrays.toString(d));
    
    }
}
