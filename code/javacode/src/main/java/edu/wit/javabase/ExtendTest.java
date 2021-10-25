package edu.wit.javabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

interface A{
    void printA();
}
interface N{
    void printN();
}
interface M extends A,N{
    void printM();
}

class MI implements M{
    @Override
    public void printA() {
        System.out.println("A");
    }

    @Override
    public void printN() {
        System.out.println("N");
    }

    @Override
    public void printM() {
        System.out.println("M");
    }
}

public class ExtendTest {
    public static void main(final String[] args) {
        final MI mi = new MI();
        mi.printA();
        mi.printN();
        mi.printM();
    }
}
