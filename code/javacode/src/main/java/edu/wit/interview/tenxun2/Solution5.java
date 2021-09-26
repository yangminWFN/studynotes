package edu.wit.interview.tenxun2;

import java.util.Scanner;

public class Solution5 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int N = Integer.parseInt(cin.nextLine());
        String[] str = cin.nextLine().split(" ");
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(str[i]);
        }
        int l = 0;
        int count = 0;
        while(l <= N -2){
            count++;
            int r = l + 1;
            int min = nums[r];
            r++;
            for(; r < N;r++){
                if(min >= nums[l] && min >= nums[r]){
                    count++;
                    min = nums[r];
                }
            }
            l++;
        }
        System.out.println(count);
    }
}
