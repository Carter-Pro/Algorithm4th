package Chapter4;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.introcs.In;

import java.io.File;

/**
 * Created by carter on 16/3/17.
 */
public class CC {
    private boolean[] marked;
    private int[] id;
    private int count;

    public CC(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                dfs(G, s);
                count++;
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (Integer w : G.adj(v)) {
            if (!marked[w])
                dfs(G, w);
        }
    }

    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        File file = new File("/Users/carter/Github/Algorithm/algs4-data/tinyG.txt");
        In in = new In(file);

        Graph G = new Graph(in);
        CC cc = new CC(G);

        int M = cc.count();
        System.out.println(M + " components");

        Bag<Integer>[] components;
        components = new Bag[M];
        for (int i = 0; i < M; i++)
            components[i] = new Bag<>();
        for (int v = 0; v < G.V(); v++)
            components[cc.id(v)].add(v);
        for (int i = 0; i < M; i++) {
            for (Integer v : components[i])
                System.out.print(v + " ");
            System.out.println();
        }
    }
}
