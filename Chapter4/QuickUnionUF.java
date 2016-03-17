package Chapter4;

import edu.princeton.cs.introcs.In;

import java.io.File;

/**
 * Created by carter on 16/3/17.
 */
public class QuickUnionUF {
    private int count;
    private int[] id;
    //以整数标识(0到N-1)初始化N个触点
    public QuickUnionUF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }
    //在p和q之间添加一条连接
    public void union(int p, int q)
    {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot)
            return;

        id[pRoot] = qRoot;

        count--;
    }
    //p(0到N-1)所在的分量的标识符
    public int find(int p) {
        while (p != id[p])
            p = id[p];
        return p;
    }
    //如果p和q存在于同一个分量重则返回true            count--;

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
    //连通分量的数量
    public int count() {
        return count;
    }

    public static void main(String[] args) {
        //tinyUF.txt mediumUF.txt largeUF.txt
        File file = new File("/Users/carter/Github/Algorithm/algs4-data/tinyUF.txt");
        In in = new In(file);

        int N = in.readInt();
        QuickUnionUF uf = new QuickUnionUF(N);
        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();
            if (uf.connected(p, q))
                continue;
            uf.union(p, q);
            System.out.println(p + " " + q);
        }
        System.out.println(uf.count() + " components");
    }
}
