package edu.wit.interview.ali;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Solution {
    static List<Integer> temp = new ArrayList<>();
    static List<List<Integer>> ans = new ArrayList<>();

    public static List<List<Integer>> findSeq(int[] nums) {
        dfs(0, Integer.MIN_VALUE, nums);
        return ans;
    }

    public static void dfs(int cur, int last, int[] nums) {
        if (cur == nums.length) {
            if (temp.size() >= 1) {
                ans.add(new ArrayList<Integer>(temp));
            }
            return;
        }
        if (nums[cur] > last) {
            temp.add(nums[cur]);
            dfs(cur + 1, nums[cur], nums);
            temp.remove(temp.size() - 1);
        }
        if (nums[cur] != last) {
            dfs(cur + 1, last, nums);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int k = scanner.nextInt();
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = scanner.nextInt();
        }
        System.out.println(findSeq(a));
        int i = 0;
        for (int j = 0; j < ans.size(); j++) {
            if(ans.get(j).size() <= k){
                i++;
            }
        }
        System.out.println(i);

    }
}
