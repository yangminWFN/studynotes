package edu.wit.wangyi;

public class Solution4 {
    //消耗
    static int[] costNum = new int[]{1,2};
    static int max = Integer.MAX_VALUE;

    public static int minPath(int[][] input){
        dfs(input,0,0,0);
        if(max == Integer.MAX_VALUE){
            return -1;
        }else{
            return max - costNum[input[0][0]];
        }
    }

    public static void dfs(int[][] input, int i,int j, int cost){
        if(i >= input.length || j >= input[0].length) return;
        if(input[i][j] == 2) return;
        if(i == input.length - 1 && j == input[0].length - 1){
            cost += costNum[input[i][j]];
            max = Math.max(max,cost);
            return;
        }
        dfs(input,i+1,j,cost+costNum[input[i][j]]);
        dfs(input,i,j+1,cost+costNum[input[i][j]]);
    }

    public static void main(String[] args) {

    }
}
