package edu.wit.javabase;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

class MyGeneralMaxHeap<E> {
    private final Comparator<? super E> comparator;
    private int capacity;
    private E[] data;
    private int size;

    public MyGeneralMaxHeap(int capacity, Comparator<? super E> comparator, Class<E> clazz) {
        this.capacity = capacity;
        this.data = (E[]) Array.newInstance(clazz, capacity + 1);
        this.comparator = comparator;
    }

    public void offer(E item) {
        assert (size + 1 <= capacity);
        size++;
        data[size] = item;
        shiftUp(size);
    }

    private void shiftUp(int k) {
        int parent = k / 2;
        while (parent >= 1 && comparator.compare(data[k], data[parent]) > 0) {
            swap(k, parent);
            k = parent;
            parent = k / 2;
        }
    }

    public E poll() {
        assert (size > 0);
        E res = data[1];
        swap(1, size);
        size--;
        shiftDown(1);
        return res;
    }

    private void shiftDown(int k) {
        int j = k * 2;
        while (j <= size) {
            if (j + 1 <= size && comparator.compare(data[j + 1], data[j]) > 0) {
                j = j + 1;
            }
            if(comparator.compare(data[j], data[k]) > 0){
                swap(j, k);
                k = j;
                j = 2 * k;
            }else break;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void swap(int i, int j) {
        E temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}

class Person{
    private String name;
    private int age;
    public Person(String name, int age){
        this.name= name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class GeneralMaxHeap {
    public static void main(String[] args) {
        int capacity = 1000;
        MyGeneralMaxHeap<Person> maxHeap = new MyGeneralMaxHeap<>(capacity, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        }, Person.class);
        Random r = new Random();
        for (int i = 0; i < capacity; i++) {
            maxHeap.offer(new Person("person"+i,r.nextInt(100)));
        }
        while(!maxHeap.isEmpty()){
            System.out.println(maxHeap.poll());
        }
    }
}
