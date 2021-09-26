package edu.wit.interview.webank;

import java.util.Scanner;

public class Solution2 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int m = cin.nextInt();
        int x = cin.nextInt();
        int y = cin.nextInt();
        int[][] grid = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                grid[i][j] = cin.nextInt();
            }
        }
        char[] directs = cin.next().toCharArray();
        char curDirect = directs[0];
        long ans = 0;
        int px = 0;
        int py = 0;
        for (int i = 0; i < directs.length; i++) {
            char d = directs[i];
            if(d != curDirect){
                ans += x;
                curDirect = d;
            }
            if(d == 'h'){
                // 左
                if(py == 0 || grid[px][py - 1] == -1){
                    ans += y;
                }else{
                    ans += Math.max(grid[px][py], grid[px][py - 1]);
                    py--;
                }
            }else if(d == 'l'){
                // 右
                if(py == m - 1 || grid[px][py + 1] == -1){
                    ans += y;
                }else{
                    ans += Math.max(grid[px][py], grid[px][py + 1]);
                    py++;
                }
            }else if(d == 'k'){
                // 上
                if(px == 0 || grid[px - 1][py] == -1){
                    ans += y;
                }else{
                    ans += Math.max(grid[px][py], grid[px - 1][py]);
                    px--;
                }
            }else if(d == 'j'){
                // 下
                if(px == n - 1 || grid[px + 1][py] == -1){
                    ans += y;
                }else{
                    ans += Math.max(grid[px][py], grid[px + 1][py]);
                    px++;
                }
            }
        }
        System.out.println(ans);
    }
}
