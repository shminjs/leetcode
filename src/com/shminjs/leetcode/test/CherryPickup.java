package com.shminjs.leetcode.test;

import java.util.Arrays;

/**
 * Created by shimin on 2017/12/3.
 */
public class CherryPickup {
    public int cherryPickup(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] front = new int[grid.length][grid[0].length];
        int count = 0;
        boolean flag = true;
        for (int i = 0; i < m; i++) {
            if (flag && grid[i][0] == 1) {
                count++;
            } else if (grid[i][0] == -1) {
                flag = false;
                count = -1;
            }
            front[i][0] = count;
        }
        flag = true;
        count = 0;
        for (int j = 0; j < n; j++) {
            if (flag && grid[0][j] == 1) {
                count++;
            } else if (grid[0][j] == -1) {
                flag = false;
                count = -1;
            }
            front[0][j] = count;
        }
        System.out.println("++++++++++ before grid");
        printArray(grid);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (grid[i][j] != -1) {
                    int left = i-1>=0?front[i-1][j]:0;
                    int up = j-1>=0?front[i][j-1]:0;
                    front[i][j] = Math.max(up, left);
                    if (front[i][j] != -1) {
                        front[i][j] += grid[i][j];
                    }
                } else {
                    front[i][j] = -1;
                }
            }
        }
        System.out.println("+++++++++++++++ front");
        printArray(front);
        if (front[m-1][n-1] <= 0) return 0;
        // 回溯，找到最大的路径，然后设置为0，？两阶段最佳是不是最佳，是最佳
        int x = m-1, y = n-1;
        count = front[m-1][n-1];
        while (x != 0 || y != 0) {
            if (grid[x][y] == 1) {
                grid[x][y] = 0;
                count--;
            }
            if (x - 1 >= 0 && front[x-1][y] == count) {
                x--;
                continue;
            }
            if (y - 1>= 0 && front[x][y-1] == count) {
                y--;
            }
        }
        if (grid[0][0] == 1) grid[0][0] = 0;
        System.out.println("++++++++ after grid");
        printArray(grid);

        int[][] backend = new int[grid.length][grid[0].length];
        count = 0;
        flag = true;
        for (int i = 0; i < m; i++) {
            if (flag && grid[i][0] == 1) {
                count++;
            } else if (grid[i][0] == -1) {
                flag = false;
                count = -1;
            }
            backend[i][0] = count;
        }
        flag = true;
        count = 0;
        for (int j = 0; j < n; j++) {
            if (flag && grid[0][j] == 1) {
                count++;
            } else if (grid[0][j] == -1) {
                flag = false;
                count = -1;
            }
            backend[0][j] = count;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (grid[i][j] != -1) {
                    int left = i-1>=0?backend[i-1][j]:0;
                    int up = j-1>=0?backend[i][j-1]:0;
                    backend[i][j] = Math.max(up, left);
                    if (backend[i][j] != -1) {
                        backend[i][j] += grid[i][j];
                    }
                } else {
                    backend[i][j] = -1;
                }
            }
        }
        System.out.println("+++++++++++ backend");
        printArray(backend);
//        return front[m-1][n-1] + backend[m-1][n-1];
        return 3;
    }

    private void printArray(int[][] aux) {
        for (int i = 0; i < aux.length; i++) {
            for (int j = 0; j < aux[0].length; j++) {
                System.out.print(aux[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println(new CherryPickup().cherryPickup(new int[][]{{1,1,1,1,0,0,0},{0,0,0,1,0,0,0},{0,0,0,1,0,0,1},{1,0,0,1,0,0,0},{0,0,0,1,0,0,0},{0,0,0,1,0,0,0},{0,0,0,1,1,1,1}}));
    }
}
