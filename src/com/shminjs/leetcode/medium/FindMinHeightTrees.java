package com.shminjs.leetcode.medium;

import java.util.*;

/**
 * Created by shimin on 2017/10/11.
 * 310
 */
public class FindMinHeightTrees {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Graph G = new Graph(n);
        for (int i = 0; i < edges.length; i++) {
            G.addEdge(edges[i][0], edges[i][1]);
        }
        List<Integer> res = new ArrayList<>();
        int depth = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int depth_cmp = new BreadthFirstDepth(G, i).height();
            if (depth_cmp < depth) {
                res.clear();
                res.add(i);
                depth = depth_cmp;
            } else if (depth_cmp == depth) {
                res.add(i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new FindMinHeightTrees().findMinHeightTrees(
                4,
                new int[][]{{1, 0}, {1, 2}, {1, 3}}
        ));
    }
}


class Graph {
    private final int V;
    private int E;
    private List<Integer>[] adj;
    public Graph(int V) {
        this.V = V; this.E = 0;
        adj = (List<Integer>[]) new List[V];
        for (int v = 0;  v < V; v++) {
            adj[v] = new LinkedList<Integer>();
        }
    }

    public int V() { return V; }
    public int E() { return E; }
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
}

class BreadthFirstDepth {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;
    private int height;

    public BreadthFirstDepth(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        this.height = 0;
        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new LinkedList<>();
        marked[s] = true;
        queue.offer(s);
        while (!queue.isEmpty()) {
            height++;
            int levelNum = queue.size();
            for (int i = 0; i < levelNum; i++) {
                int v = queue.poll();
                for (int w : G.adj(v)) {
                    if (!marked[w]) {
                        edgeTo[w] = v;
                        marked[w] = true;
                        queue.offer(w);
                    }
                }
            }
        }
    }

    public int height() { return height; }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> stack = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            stack.push(x);
        }
        stack.push(s);
        return stack;
    }
}
