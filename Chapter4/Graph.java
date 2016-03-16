package Chapter4;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.introcs.In;

import java.io.File;

/**
 * Created by carter on 16/3/16.
 */
public class Graph {
    private static final String NEWLINE = System.getProperty("line.separator");
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<>();
    }

    public Graph(In in) {
        //读取v并将图初始化
        this(in.readInt());
        E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }

    }
    public int V()
    {
        return V;
    }
    public int E()
    {
        return E;
    }
    public void addEdge(int v, int w)
    {
        adj[v].add(w);
        adj[w].add(v);
    }
    public Iterable<Integer> adj(int v)
    {
        return adj[v];
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
    public static void main(String[] args) {
        File file = new File("/Users/carter/Github/Algorithm/algs4-data/mediumG.txt");
        In in = new In(file);

        Graph graph = new Graph(in);
        System.out.println(graph);
    }
}
