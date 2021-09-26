package edu.wit.interview.aiqiyi;

import java.util.Scanner;

public class Solution1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] strings = line.split(",");
        int[] nums = new int[strings.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = new Integer(strings[i]);
        }
        if(nums.length == 2){
            System.out.println(Math.abs(nums[0] - nums[1]));
        }
        int first = nums[0];
        int lastK = 0;
        int k = 0;
        int max = 0;
        for(int i = 1; i < nums.length; i++){
            int temp = nums[i] - nums[i - 1];
            if(temp > 0){
                k = 1;
            }else if(temp < 0){
                k = -1;
            }else{
                k = 0;
            }
            if(lastK != k){
                lastK = k;
                max = Math.max(max, Math.abs(nums[i - 1]) - first);
                first = nums[i - 1];
            }
        }
        max = Math.max(max, Math.abs(nums[nums.length - 1]) - first);
        System.out.println(max);
    }
}
