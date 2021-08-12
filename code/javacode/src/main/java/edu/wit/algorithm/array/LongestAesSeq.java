package edu.wit.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LongestAesSeq {

    /**
     * 找到最长递增子序列的长度
     *
     * @param nums
     * @return
     */
    public static int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        // 维护一个[i,j)的一个滑动窗口
        int i = 0;
        int max = 1;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j - 1] >= nums[j]) {
                i = j;
            }
            max = Math.max(max, j - i + 1);
        }
        return max;
    }

    /**
     * 找到最长递增子序列
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> findLCIS(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();
        // 维护一个[i,j)的一个滑动窗口
        int i = 0;
        int max = 1;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j - 1] >= nums[j]) {
                i = j;
            }
            max = Math.max(max, j - i + 1);
        }
        i = 0;
        List<List<Integer>> res = new ArrayList<>();
        for (int j = 1; j < nums.length; j++) {
            if (nums[j - 1] >= nums[j]) {
                i = j;
            }
            if (j - i + 1 == max) {
                List<Integer> list = new ArrayList<>();
                for (int m = i; m <= j; m++) {
                    list.add(nums[m]);
                }
                res.add(list);
            }
        }
        return res;
    }

    public static int[] generateRandomArray(int n, int min, int max) {
        Random r = new Random();
        return r.ints(min, max).limit(n).toArray();
    }

    public static void main(String[] args) {
        int n = 10000*10000;
        int min = 0;
        int max = 1000;
//        int[] nums = new int[]{1, 3, 5, 4, 7, 9, 5, 6, 7, 8};
        int[] nums = generateRandomArray(n, min, max);
        long start1 = System.currentTimeMillis();
        findLCIS(nums);
        long end1 = System.currentTimeMillis();
        System.out.println((end1 - start1) / 1000.0);

        long start2 = System.currentTimeMillis();
        findLengthOfLCIS(nums);
        long end2 = System.currentTimeMillis();
        System.out.println((end1 - start1) / 1000.0);

        //输出均为 0.832，所以它们的平均时间复杂度都为O(n)

    }
}
