package Chapter3;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SequentialSearchST;

/**
 * Created by carter on 16/3/15.
 */
public class SeprarateChainingHashST<Key extends Comparable<Key>, Value> {
    //键值对总数
    private int N;
    //散列表大小
    private int M;
    private SequentialSearchST<Key, Value>[] st;

    public SeprarateChainingHashST()
    {
        this(997);
    }

    public SeprarateChainingHashST(int M)
    {
        //创建M条链表
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++)
            st[i] = new SequentialSearchST<>();
    }
    private int hash(Key key)
    {
        return (key.hashCode() & 0x7fffffff) % M;
    }
    public Value get(Key key)
    {
        return st[hash(key)].get(key);
    }
    public void put(Key key, Value val)
    {
        st[hash(key)].put(key, val);
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
    }

}
