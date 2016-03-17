package Chapter4;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.introcs.In;

import java.io.File;

/**
 * Created by carter on 16/3/17.
 */
public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private int s;

    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }
    private void bfs(Graph G, int s)
    {
        Queue<Integer> queue = new Queue<Integer>();
        marked[s] = true;
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            int v= queue.dequeue();
            for (Integer w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.enqueue(w);
                }
            }
        }
    }
    public boolean hasPathTo(int v)
    {
        return marked[v];
    }
    public Iterable<Integer> pathTo(int v)
    {
        if (!hasPathTo(v))
            return null;
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }
    public static void main(String[] args) {
        File file = new File("/Users/carter/Github/Algorithm/algs4-data/tinyCG.txt");
        In in = new In(file);
        int s = 0;
        Graph G = new Graph(in);
        BreadthFirstPaths paths = new BreadthFirstPaths(G, s);
        for (int v = 0; v < G.V(); v++) {
            System.out.print(s + " " + v + ": ");
            if (paths.hasPathTo(v))
                for (Integer x : paths.pathTo(v)) {
                    if (x == s)
                        System.out.print(x);
                    else
                        System.out.print("-" + x);
                }
            System.out.println();
        }
    }
}
