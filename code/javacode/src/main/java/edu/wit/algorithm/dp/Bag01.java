package edu.wit.algorithm.dp;

import java.util.Scanner;

/**
 * 01背包问题
 */
public class Bag01 {

    /**
     * 测试：
     * 请输入总物品数n和背包容量C
     * 4 10
     * 请输入每一个物品的容量和价值
     * 2 2
     * 3 4
     * 5 3
     * 5 7
     * 最大价值为：13
     */

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("请输入总物品数n和背包容量C");
//        int n = scanner.nextInt();
//        int c = scanner.nextInt();
//        int[] w = new int[n + 1];
//        int[] v = new int[n + 1];
//        System.out.println("请输入每一个物品的容量和价值");
//        for (int i = 1; i <= n; i++) {
//            w[i] = scanner.nextInt();
//            v[i] = scanner.nextInt();
//        }
//        //定义状态：dp[i][j]表示前 i 个物品，在背包容量为 j 的情况下，可以获得的最大价值
//        int[][] dp = new int[n + 1][c + 1];
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= c; j++) {
//                //状态转移方程
//                if (j < w[i]) {
//                    dp[i][j] = dp[i - 1][j];
//                } else {
//                    //放入该物品或不放入该物品，取更大的
//                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i]] + v[i]);
//                }
//            }
//        }
//        System.out.println("最大价值为：" + dp[n][c]);
//        //时间复杂度为 O(nc)，空间复杂度为O(nc)
//    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入总物品数n和背包容量C");
        int n = scanner.nextInt();
        int c = scanner.nextInt();
        int[] w = new int[n + 1];
        int[] v = new int[n + 1];
        System.out.println("请输入每一个物品的容量和价值");
        for (int i = 1; i <= n; i++) {
            w[i] = scanner.nextInt();
            v[i] = scanner.nextInt();
        }
        //定义状态：dp[j]表示前 i 个物品，在背包容量为 j 的情况下，可以获得的最大价值
        //只会用到前面第 i - 1行的数据，所以只用使用一维数组就可以保存状态了
        int[] dp = new int[c + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = c; j >= 1; j--) {
                //状态转移方程
                if(j < w[i]){
                    //就等于前i - 1个物品的最大价值
                    dp[j] = dp[j];
                }else{
                    dp[j] = Math.max(dp[j - w[i]] + v[i], dp[j]);
                }
            }
        }
        System.out.println("最大价值为：" + dp[c]);
        //时间复杂度为 O(nc)，空间复杂度为O(c)
    }

}
