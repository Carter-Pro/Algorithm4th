package Chapter4;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.introcs.In;

import java.io.File;

/**
 * Created by carter on 16/3/16.
 */
public class DepthFirstSearch {
    private boolean[] marked;
    private int count;
    public DepthFirstSearch(Graph G, int s){
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        count++;
        for (Integer w : G.adj(v)) {
            if (!marked[w])
                dfs(G, w);
        }
    }

    public boolean marked(int w) {
        return marked[w];
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        File file = new File("/Users/carter/Github/Algorithm/algs4-data/tinyCG.txt");
        In in = new In(file);

        Graph G = new Graph(in);
        int s = 0;
        DepthFirstSearch search = new DepthFirstSearch(G, s);
        for (int v = 0; v < G.V(); v++)
            if (search.marked(v))
                System.out.println(v + " ");
        System.out.println();

        if (search.count() != G.V())
            System.out.println("NOT connected");
    }
}
