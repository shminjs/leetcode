package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/5.
 * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water. Grid cells are connected
 * horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e.,one or more connected
 * land cells). The island doesn't have "lakes" (water inside that isn't connected to the water around the island). one cell is a square
 * with side length 1. The grid is rectangular, width and height don't exceed 100. Detemine the primeter of the island.
 */
public class IslandPerimeter {
    public int islandPerimeter(int[][] grid) {
        int r = grid.length, c = grid[0].length;
        int count = 0;
        int neighbours = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 1) {
                    count++;
                    if (i < r - 1 && grid[i + 1][j] == 1) neighbours++;
                    if (j < c - 1 && grid[i][j + 1] == 1) neighbours++;
                }
            }
        }
        return count * 4 - neighbours * 2;
    }

    public static void main(String[] args) {
//        int[][] input = {{0, 1, 0, 0},{1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}};
        int[][] input = {{1}, {0}};
        IslandPerimeter ip = new IslandPerimeter();
        int result = ip.islandPerimeter(input);
        System.out.println(result);
    }

    //    这个答案，修改了一下居然就没超时。
    public int islandPerimeter2(int[][] grid) {
        int r = grid.length, c = grid[0].length;
        int count = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 1) {
                    if (j - 1 == -1 || grid[i][j - 1] == 0) {
                        count++;
                    }
                    if (i + 1 == r || grid[i + 1][j] == 0) {
                        count++;
                    }
                    if (j + 1 == c || grid[i][j + 1] == 0) {
                        count++;
                    }
                    if (i - 1 == -1 || grid[i - 1][j] == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
