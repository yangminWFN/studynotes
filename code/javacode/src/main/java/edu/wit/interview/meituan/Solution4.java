package edu.wit.interview.meituan;

import java.util.HashSet;
import java.util.Scanner;

public class Solution4 {


    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int N = Integer.parseInt(cin.nextLine());
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] params = cin.nextLine().split(" ");
            for (int j = 0; j < params.length; j++) {
                map[i][j] = Integer.parseInt(params[j]);
            }
        }
        boolean[] vt = new boolean[N];
        for (int i = 0; i < N; i++) {
            vt[i] = true;
            dfs(i, 1, i, map, vt);
            vt[i] = false;
        }
        System.out.println(result.size());
    }

    static HashSet<String> result = new HashSet<>();

    public static void dfs(int start, int depth, int index, int[][] map, boolean[] visited) {
        if (depth > 4) {
            return;
        }
        if (depth == 4) {
            if (map[index][start] == 1) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < visited.length; i++) {
                    if (visited[i]) {
                        sb.append(i);
                    }
                }
                if (!result.contains(sb.toString())) {
                    result.add(sb.toString());
                }
                return;
            }
            for (int i = 0; i < map[index].length; i++) {
                if (map[index][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    dfs(start, depth + 1, i, map, visited);
                    visited[i] = false;
                }
            }


        }
    }
}
