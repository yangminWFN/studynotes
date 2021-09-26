package edu.wit.interview.webank;

import java.util.Scanner;

public class Solution1 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = cin.nextInt();
        }
        int[] a = new int[n];
        a[0] = b[0];
        for(int i = 1; i < n; i++){
            if(i % 2 == 0){
                a[i] = b[i] - b[i - 1];
            }else{
                a[i] = b[i - 1] - b[i];
            }
        }
        for(int i = 0; i < n; i++){
            if(i != n - 1){
                System.out.print(a[i] + " ");
            }else{
                System.out.print(a[i]);
            }

        }
    }
}
