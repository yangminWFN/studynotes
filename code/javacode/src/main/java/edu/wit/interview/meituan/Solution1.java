package edu.wit.interview.meituan;

import java.util.LinkedList;
import java.util.Scanner;

public class Solution1 {
    static int res = 0;

    public static int getNum(int n) {
        LinkedList<Integer> track = new LinkedList<>();
        backTrack(track, n);
        return res % 998244353;
    }

    public static void backTrack(LinkedList<Integer> track, int n) {
        if (track.size() == n) {
            res += 1;
            return;
        }
        for (int i = 0; i < 2; i++) {
            if(track.size() >= 2){
                int first = track.get(track.size()-1);
                int second = track.get(track.size()-2);
                if(first != i && second == i){
                    continue;
                }
            }
            track.add(i);
            backTrack(track,n);
            track.remove();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(getNum(n));
    }
}
