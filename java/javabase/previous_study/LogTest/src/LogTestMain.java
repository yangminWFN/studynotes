import java.util.logging.Logger;

class Pair<T, U> {
    private T firstName;
    private U secondName;

    public Pair(T firstName, U secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public Pair() {
        firstName = null;
        secondName = null;
    }

    public T getFirstName() {
        return firstName;
    }

    public U getSecondName() {
        return secondName;
    }

    public void setFirstName(T firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(U secondName) {
        this.secondName = secondName;
    }
}

class ArrayUtil {
    public static <T> T getMiddle(T[] a) {
        return a[a.length / 2];
    }

    public static <T, V> String add(T first, V next) {
        return first.toString() + next.toString();
    }

    public static <T extends Comparable> T getMin(T[] a) {
        if (a == null || a.length == 0) {
            return null;
        }
        T min = a[0];
        for (int i = 0; i < a.length; i++) {
            if(min.compareTo(a[i])>0)
                min=a[i];
        }
        return min;
    }
}

class Apple implements Comparable<Apple>{
    private double weight;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    public Apple(double w){
        this.weight=w;
    }

    @Override
    public String toString() {
        return "重量为："+this.weight;
    }

    @Override
    public int compareTo(Apple o) {
        if(this.weight>o.weight) return 1;
        if(this.weight<o.weight) return -1;
        return 0;
    }
}

public class LogTestMain {
    public static void main(String[] args) {
//        String firstName = "yangmin";
//        Integer age = 10;
//        Pair<String, Integer> record = new Pair<String, Integer>(firstName, age);
//        System.out.println("Name:" + record.getFirstName() + ",age:" + record.getSecondName());
//        record.setSecondName(100);
//        System.out.println("Name:" + record.getFirstName() + ",age:" + record.getSecondName());
//
//        String[] arrs = {"name", "yangmin", "hello"};
//        System.out.println(ArrayUtil.getMiddle(arrs));
//        String testStr = ArrayUtil.<String, Integer>add("yangmin", 100);
//        System.out.println(testStr);
        Integer[] intArr=new Integer[]{1,5,2,3,6,9};
        System.out.println(ArrayUtil.getMin(intArr));
        Apple[] apples=new Apple[]{new Apple(10.0),new Apple(9.8),new Apple(7.9),new Apple(11.3)};
        System.out.println(ArrayUtil.getMin(apples));
        Pair erasePair=new Pair("yangmin",100);
        System.out.println(erasePair.getFirstName());
        System.out.println(erasePair.getSecondName());
    }

}
