package edu.wit.javabase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Apple {
    private String color;
    private Double weight;

    public Apple() {
    }

    public Apple(String color, Double weight) {
        this.color = color;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}

public class LambdaTest {


    public static void testPredicate(List<Apple> apples) {
        Predicate<Apple> p1 = a -> "blue".equals(a.getColor());
        Predicate<Apple> p2 = p1.and(a -> a.getWeight() > 50.0);
        List<Apple> result1 = apples.stream().filter(p2).collect(Collectors.toList());
        System.out.println(result1);
    }

    public static void testComparator(List<Apple> apples) {
        System.out.println("初始序列：");
        System.out.println(apples);
        System.out.println("首先按颜色逆序排序，然后按重量正序排序：");
        apples.sort(Comparator.comparing(Apple::getColor).reversed().thenComparing(Apple::getWeight));
        System.out.println(apples);
    }

    public static void testFunction(List<Apple> apples) {
        Function<Apple, String> f1 = Apple::getColor;
        List<String> colors = apples.stream().map(f1).distinct().collect(Collectors.toList());
        System.out.println(colors);

        Function<Integer, Integer> f2 = x -> x + 1;
        Function<Integer, Integer> f3 = x -> x * 2;
//        Function<Integer, Integer> f4 = f2.compose(f3);  // (10*2)+1
        Function<Integer, Integer> f4 = f2.andThen(f3);  // (10+1)*2
        System.out.println(f4.apply(10));
    }

    public static double getK(Function<Double, Double> f, double a, double b) {
        // 求函数 f，在(a,b)区间的平均斜率
        double k = (f.apply(b) - f.apply(a)) / (b - a);
        return k;
    }

    public static void testDesignFunction() {
        Function<Double, Double> f = a -> 5 * a;
        System.out.println(getK(f, 10.0, 100.0));

        Function<Double,Double> f2 = a -> a*a + 1;
        System.out.println(getK(f2,-10,0));
    }

    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("red", 100.0));
        apples.add(new Apple("blue", 50.0));
        apples.add(new Apple("blue", 60.0));
        apples.add(new Apple("yellow", 80.0));
        apples.add(new Apple("yellow", 55.0));
        apples.add(new Apple("pink", 30.0));

    }
}
