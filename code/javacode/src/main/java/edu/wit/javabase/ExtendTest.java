package edu.wit.javabase;

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
    public static void main(String[] args) {
        MI mi = new MI();
        mi.printA();
        mi.printN();
        mi.printM();
    }
}
