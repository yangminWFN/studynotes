package edu.wit.algorithm.backtrack;

import java.util.LinkedList;
import java.util.List;

public class AllClimbPathTest {

    public static void backTrack(int n, int[] arr, LinkedList<Integer> track, List<List<Integer>> res) {
        //如果满足条件则记录当前路径
        if (n == 0) {
            res.add(new LinkedList<>(track));
            return;
        }
        for (int value : arr) {
            if (n >= value) {
                //做选择
                track.add(value);
                //递归调用
                backTrack(n - value, arr, track, res);
                //撤销选择
                track.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3};
        int n = 7;
        List<List<Integer>> res = new LinkedList<>();
        LinkedList<Integer> track = new LinkedList<>();
        backTrack(n, arr, track, res);
        System.out.println(res);


    }
}
