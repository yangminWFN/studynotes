package edu.wit.algorithm.array;

import java.util.*;

public class ThreeSum {
    static class Pair {
        int first;
        int second;
        int third;

        public Pair(int first, int second, int third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        @Override
        public boolean equals(Object o) {
            if (o != this) return false;
            if (o != null || o.getClass() != getClass()) return false;
            Pair other = (Pair) o;
            return first == other.first && second == other.second && third == other.third;
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second, third);
        }

        public List<Integer> toList() {
            List<Integer> list = new ArrayList<>(Arrays.asList(first, second, third));
            return list;
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length <= 2) return new ArrayList<>();
        Set<Pair> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int m = j + 1; m < nums.length; m++) {
                    if (nums[i] + nums[j] + nums[m] == 0) {
                        set.add(new Pair(nums[i], nums[j], nums[m]));
                    }
                }
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        for (Pair p : set) {
            res.add(new ArrayList<>(Arrays.asList(p.first, p.second, p.third)));
        }
        return res;
    }

    public static void swap(int[] a, int i, int j) {
        if(i == j) return;
        a[i] = a[i] ^ a[j];
        a[j] = a[i] ^ a[j];
        a[i] = a[i] ^ a[j];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1};
        swap(nums, 0 ,0);
        System.out.println(Arrays.toString(nums));
    }
}
