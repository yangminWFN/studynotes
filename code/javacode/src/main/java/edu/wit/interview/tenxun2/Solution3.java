package edu.wit.interview.tenxun2;

import java.util.Scanner;

public class Solution3 {


    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int N = Integer.parseInt(cin.nextLine());
        if (N <= 1) {
            System.out.println(N);
            return;
        }
        String str = cin.nextLine();
        dfs(str, 1, 1, str.charAt(0), 1);
        System.out.println(result);
    }

    static int result = 0;

    public static void dfs(String str, int max, int index, char pre, int value) {
        if (index >= str.length()) {
            result = Math.max(result, max);
            return;
        }
        if (str.charAt(index) == pre) {
            dfs(str, max + value + 1, index + 1, str.charAt(index), value + 1);
            dfs(str, max, index + 1, pre, value);

        } else {
            dfs(str, max + 1, index + 1, str.charAt(index), 1);
            dfs(str, max, index + 1, pre, value);
        }

    }
}
