package edu.wit.interview;

public class QianxinTest {
    int res = 0;
    int tmp = 0;

    public  int getMaximumResource(int[][] grid) {

        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                boolean[][] visit = new boolean[m][n];
                dfs(grid, i, j, visit);
            }
        }
        return res;
    }

    private void dfs(int[][] grid, int i, int j, boolean[][] visit) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0 || visit[i][j]) {
            res = Math.max(res, tmp);
            return;
        }

        visit[i][j] = true;
        tmp += grid[i][j];
        // right
        dfs(grid, i, j + 1, visit);
        // bottom
        dfs(grid, i + 1, j, visit);
        // left
        dfs(grid, i, j - 1, visit);
        // top
        dfs(grid, i - 1, j, visit);
        tmp -= grid[i][j];
        visit[i][j] = false;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {0, 3, 4},
                {8, 5, 0},
                {0, 9, 2}
        };

        System.out.println(new QianxinTest().getMaximumResource(grid));
    }
}
