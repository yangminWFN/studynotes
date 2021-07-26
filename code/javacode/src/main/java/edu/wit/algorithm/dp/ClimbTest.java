package edu.wit.algorithm.dp;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

public class ClimbTest {

//    public static int climb(int n) {
//        if(n == 1) return 1;
//        if(n == 2) return 2;
//        else return climb(n-1)+climb(n -2);
//    }

    public static int climb(int n) {
        //第一个数
        int first = 1;
        //第二个数
        int second = 1;
        //第三个数
        int res = 1;
        for (int i = 1; i < n; i++) {
            res = first + second;
            first = second;
            second = res;
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println(climb(n));

    }
}
