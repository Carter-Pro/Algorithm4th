package Chapter4;

import edu.princeton.cs.introcs.In;

import java.io.File;

/**
 * Created by carter on 16/3/17.
 */
public class QuickFindUF {
    private int count;
    private int[] id;
    //以整数标识(0到N-1)初始化N个触点
    public QuickFindUF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }
    //在p和q之间添加一条连接
    public void union(int p, int q)
    {
        int pID = find(p);
        int qID = find(q);
        if (pID == qID)
            return;
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID)
                id[i] = qID;
        }
        count--;
    }
    //p(0到N-1)所在的分量的标识符
    public int find(int p) {
        return id[p];
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
        File file = new File("/Users/carter/Github/Algorithm/algs4-data/largeUF.txt");
        In in = new In(file);

        int N = in.readInt();
        QuickFindUF quickFindUF = new QuickFindUF(N);
        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();
            if (quickFindUF.connected(p, q))
                continue;
            quickFindUF.union(p, q);
//            System.out.println(p + " " + q);
        }
        System.out.println(quickFindUF.count() + " components");
    }
}
