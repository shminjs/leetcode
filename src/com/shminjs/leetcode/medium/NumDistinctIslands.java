package com.shminjs.leetcode.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by shimin on 2017/10/10.
 * 694
 */
public class NumDistinctIslands {
    private static int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Set<String> set = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, visited, i, j, 0, sb);
                    set.add(sb.toString());
                }
            }
        }
        return set.size();
    }

    private void dfs(int[][] grid, boolean[][] visited, int x, int y, int k, StringBuilder sb) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) {
            sb.append("|");
        }
        if (visited[x][y]) return;
        visited[x][y] = true;
        if (k == 1) sb.append("l");
        else if (k == 2) sb.append("d");
        else if (k == 3) sb.append("r");
        else if (k == 4) sb.append("u");
        else sb.append(grid[x][y]);
        int ind = 0;
        for (int[] dir : dirs) {
            ind++;
            dfs(grid, visited, x + dir[0], y + dir[1], ind, sb);
        }
    }
}
