package edu.wit.javabase;


import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return name;
    }

    public enum Type {MEAT, FISH, OTHER}
}

public class StremTest {
    public static List<Dish> menu = null;

    static {
        menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
    }

    public static void test1() {
        List<String> result = menu.stream()
                .filter(x -> {
                    System.out.println("filtering " + x.getName());
                    return x.getCalories() > 300;
                })
                .map(x -> {
                    System.out.println("mapping " + x.getName());
                    return x.getName();
                })
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(result);
    }

    public static void test2() {
        String[] words = new String[]{"hello", "world", "yangmin", "min"};
        List<Character> result = Arrays.stream(words).map(s -> s.split("")).flatMap(s -> Arrays.stream(s)).map(s -> s.charAt(0)).collect(Collectors.toList());
        System.out.println(result);

        List<Integer> nums1 = Arrays.asList(1, 2, 3, 4);
        List<Integer> nums2 = Arrays.asList(5, 6);
        List<List<Integer>> res2 = nums1.stream()
                .flatMap(i -> nums2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> Arrays.asList(i, j)))
                .collect(Collectors.toList());
        System.out.println(res2);
    }

    public static void test3() {
        List<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        BinaryOperator<Integer> operator = (a, b) -> a + b;
        Optional<Integer> res = nums.stream().reduce(operator);
        System.out.println(res.orElse(0));

        Optional<Integer> max = nums.stream().reduce(Math::max);
        System.out.println(max.orElse(0));

        Optional<Integer> count = nums.stream().map(x -> 1).reduce((a, b) -> a + b);
        System.out.println(count.orElse(0));

        int sum = nums.stream().mapToInt(x -> x).sum();
        OptionalInt max1 = nums.stream().mapToInt(x -> x).max();
        System.out.println(max1.orElse(0));
        int[] ints = IntStream.rangeClosed(1, 100).limit(100).toArray();
        System.out.println(Arrays.toString(ints));
    }

    public static void generateTriples(){
        List<double[]> list = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                        .filter(arr -> arr[2] % 1 == 0)
                ).collect(Collectors.toList());
        list.forEach(x-> System.out.println(Arrays.toString(x)));
    }

    public static void getWords(){
        try(Stream<String> lines = Files.lines(Paths.get("data/nio/test.txt"),Charset.defaultCharset());){
            final List<String> words = lines.flatMap(s -> Arrays.stream(s.split(" "))).distinct().collect(Collectors.toList());
            System.out.println(words);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        getWords();
        
    }
}
