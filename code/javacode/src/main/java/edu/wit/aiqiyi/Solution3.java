package edu.wit.aiqiyi;

import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class Solution3 {
    public static void main(String[] args) {
        final Scanner reader = new Scanner(System.in);
        int n = reader.nextInt();
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(n);
        new Thread(() -> {
            try {
                zeroEvenOdd.printZero(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.printEven(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.printOdd(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

class ZeroEvenOdd {
    private int n;

    // 定义三个信号量
    Semaphore zero = new Semaphore(1);
    Semaphore even = new Semaphore(0);
    Semaphore odd = new Semaphore(0);

    volatile int count = 1;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void printZero(IntConsumer printNumber) throws InterruptedException {
        while(count <= n){
            zero.acquire();
            if(count > n){
                even.release();
                odd.release();
                break;
            }
            printNumber.accept(0);
            if((count & 1) == 0){
                even.release();
            }else{
                odd.release();
            }
        }
    }

    public void printEven(IntConsumer printNumber) throws InterruptedException {
        while(count <= n){
            even.acquire();
            if(count > n) break;
            printNumber.accept(count);
            count++;
            zero.release();
        }
    }

    public void printOdd(IntConsumer printNumber) throws InterruptedException {
        while(count <= n){
            odd.acquire();
            if(count > n) break;
            printNumber.accept(count);
            count++;
            zero.release();
        }
    }


}
