package com.shminjs.leetcode.medium;

import java.util.*;

/**
 * Created by shimin on 2017/10/10.
 * 684
 */
public class FindRedundantConnection {
    public int[] findRedundantConnection(int[][] edges) {
        int max = 0;
        for (int i = 0; i < edges.length; i++) {
            max = Math.max(max, Math.max(edges[i][0], edges[i][1]));
        }
        Digraph graph = new Digraph(max);
        for (int i = 0; i < edges.length; i++) {
            graph.addEdge(edges[i][0]-1, edges[i][1]-1);
        }
        DirectedCycle dc = new DirectedCycle(graph);
        int m1 = 0, m2 = 0;
        for (int i = 0; i < edges.length; i++) {
            if (dc.containsInCycle(edges[i][0]-1) && dc.containsInCycle(edges[i][1]-1)) {
                m1 = edges[i][0];
                m2 = edges[i][1];
            }
        }
        return new int[]{m1, m2};
    }

    public static void main(String[] args) {
        int[][] edges = {{1, 2}, {1, 3}, {2, 3}};
        System.out.println(Arrays.toString(new FindRedundantConnection().findRedundantConnection(edges)));
    }
}

class Digraph {
    private final int V;
    private int E;
    private List<Integer>[] adj;

    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (List<Integer>[]) new List[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new ArrayList<Integer>();
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
    public Digraph reverse() {
        Digraph R = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                R.addEdge(w, v);
            }
        }
        return R;
    }
}

class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private Set<Integer> cycle;
    private boolean[] onStack;

    public DirectedCycle(Digraph G) {
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) dfs(G, v);
        }
    }

    private void dfs(Digraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (this.hasCycle()) return;
            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            } else if (onStack[w]) {
                cycle = new HashSet<>();
                for (int x = v; x != w; x = edgeTo[x])
                    cycle.add(x);
                cycle.add(w);
                cycle.add(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Set<Integer> cycle() {
        return cycle;
    }

    public boolean containsInCycle(int w) {
        return cycle == null ? false : cycle.contains(w);
    }
}