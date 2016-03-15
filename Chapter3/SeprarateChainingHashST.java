package Chapter3;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.SequentialSearchST;
import edu.princeton.cs.introcs.In;

import java.io.File;

/**
 * Created by carter on 16/3/15.
 */
public class SeprarateChainingHashST<Key extends Comparable<Key>, Value> {
    private static final int INIT_CAPACITY = 4;
    //键值对总数
    private int N;
    //散列表大小
    private int M;
    private SequentialSearchST<Key, Value>[] st;

    public SeprarateChainingHashST()
    {
        this(INIT_CAPACITY);
    }

    public SeprarateChainingHashST(int M)
    {
        //创建M条链表
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++)
            st[i] = new SequentialSearchST<>();
    }
    private void resize(int chains)
    {
        SeprarateChainingHashST<Key, Value> temp = new SeprarateChainingHashST<>(chains);
        for (int i = 0; i < M; i++) {
            for (Key key : st[i].keys()) {
                temp.put(key, st[i].get(key));
            }
        }
        this.N = temp.N;
        this.M = temp.M;
        this.st = temp.st;
    }
    private int hash(Key key)
    {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }
    public Value get(Key key)
    {
        if (key == null)
            throw new NullPointerException("argument to get() is null");
        return st[hash(key)].get(key);
    }
    public void put(Key key, Value val)
    {
        if (key == null)
            throw new NullPointerException("first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }
        //顺序链表的平均长度大于等于10则调整数组大小
        if (N >= 10*M)
            resize(2*M);
        int i = hash(key);
        if (!st[i].contains(key))
            N++;
        st[i].put(key, val);
    }
    //practice 3.1.5
    public void delete(Key key)
    {
        if (key == null)
            throw new NullPointerException("argument to delete() is null");
        int i = hash(key);
        if (st[i].contains(key)) {
            N--;
            st[i].delete(key);
        }
        //顺序链表的平均长度小于等于2则调整数组大小
        if (M > INIT_CAPACITY && N <= 2*M)
            resize(M/2);
    }
    //practice 3.4.19
    public Iterable<Key> keys()
    {
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < M; i++) {
            for (Key key : st[i].keys()) {
                queue.enqueue(key);
            }
        }
        return queue;
    }public static void main(String[] args) {
        //int minlen = 8;
        SeprarateChainingHashST<String, Integer> st = new SeprarateChainingHashST<String, Integer>();

        File file = new File("/Users/carter/Github/Algorithm/algs4-data/tinyTale.txt");
        In in = new In(file);

        for (int i = 0; !in.isEmpty(); i++) {
            String key = in.readString();
            st.put(key, i);
        }
        for (String s : st.keys()) {
            System.out.println(s + " " + st.get(s));
        }
    }

}
