package Chapter3;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.introcs.In;

import java.io.File;
import java.util.Iterator;
import java.util.StringJoiner;

/**
 * Created by carter on 16/3/15.
 */
public class LinearProbingHashST<Key extends Comparable<Key>, Value>{
    private static final int INIT_CAPACITY = 4;
    private int N;
    private int M = 16;
    private Key[] keys;
    private Value[] vals;

    public LinearProbingHashST() {
        this(INIT_CAPACITY);
    }

    public LinearProbingHashST(int capacity) {
        M = capacity;
        //key extends Comparable
        keys = (Key[]) new Comparable[M];
        vals = (Value[]) new Object[M];
    }
    public int size()
    {
        return N;
    }
    public boolean isEmpty()
    {
        return size() == 0;
    }
    public boolean contains(Key key) {
        if (key == null)
            throw new NullPointerException("argument to contains() is null.");
        return get(key) != null;
    }
    private int hash(Key key)
    {
        return (key.hashCode() & 0x7fffffff) % M;
    }
    private void resize(int capacity)
    {
        LinearProbingHashST<Key, Value> t;
        t = new LinearProbingHashST<>(capacity);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null)
                t.put(keys[i], vals[i]);
        }
        keys = t.keys;
        vals = t.vals;
        M = t.M;
    }
    public void put(Key key, Value val)
    {
        if (key == null)
            throw new NullPointerException("argument to put() is null.");
        //如果val为null,则认为该put操作要删除key
        if (val == null) {
            delete(key);
            return;
        }
        if (N >= M/2)
            resize(2*M);
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
                if (keys[i].equals(key))
                {
                    vals[i] = val;
                    return;
                }
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }
    public Value get(Key key)
    {
        if (key == null)
            throw new NullPointerException("argument to get() is null.");
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key))
                return vals[i];
        return null;
    }
    public void delete(Key key)
    {
        if (key == null)
            throw new NullPointerException("argument to delete() is null.");
        if (!contains(key))
            return;
        int i = hash(key);
        while (!keys[i].equals(key))
            i = (i + 1) % M;
        keys[i] = null;
        vals[i] = null;
        i = (i + 1) % M;
        while (keys[i] != null)
        {
            Key keyToRedo = keys[i];
            Value valToRedo = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;
            put(keyToRedo, valToRedo);
            i = (i + 1) % M;
        }
        N--;
        if (N > 0 && N == M/8)
            resize(M/2);
    }

    //practice 3.4.19
    public Iterable<Key> keys()
    {
        Queue<Key> queue = new Queue<>();
        for (Key key : keys) {
            if (key != null)
                queue.enqueue(key);
        }
        return queue;
    }
    public static void main(String[] args) {
        //int minlen = 8;
        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<String, Integer>();

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
