package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/10/8.
 */
public class MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        UF uf = new UF(m * n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    if (j + 1 < n && grid[i][j+1] == 1) {
                        uf.union(i * n + j, i * n + j + 1);
                    }
                    if (i + 1 < m && grid[i+1][j] == 1) {
                        uf.union(i * n + j, (i + 1) * n + j);
                    }
                }
            }
        }
        if (uf.count() == m * n) {
            return 0;
        } else {
            return uf.maxComponent();
        }
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};
        System.out.println(new MaxAreaOfIsland().maxAreaOfIsland(grid));
    }
}

class UF {
    private int[] ids;
    private int count;
    private int[] sz;

    public UF(int N) {
        this.ids = new int[N];
        for (int i = 0; i < N; i++) { ids[i] = i; }
        this.count = N;
        this.sz = new int[N];
        for (int i = 0; i < N; i++) { sz[i] = 1; }
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    private int find(int p) {
        while (p != ids[p]) p = ids[p];
        return p;
    }

    public int count() { return count; }

    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) return;

        if (sz[i] < sz[j]) {
            ids[i] = j;
            sz[j] += sz[i];
        } else {
            ids[j] = i;
            sz[i] += sz[j];
        }
        count--;
    }

    public int maxComponent() {
        int max = 0;
        for (int i = 0; i < sz.length; i++) {
            max = Math.max(sz[i], max);
        }
        return max;
    }
}
