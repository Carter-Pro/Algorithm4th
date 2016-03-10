package Chapter3;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.introcs.In;
import sun.jvm.hotspot.debugger.posix.elf.ELFSectionHeader;

import java.io.File;

/**
 * Created by carter on 16/3/10.
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] vals;
    private int N;

    public BinarySearchST(int capacity)
    {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }
    public int size()
    {
        return N;
    }
    public boolean isEmpty()
    {
        return N == 0;
    }
    //rank()返回小于key的键的数量
    public int rank(Key key)
    {
        //迭代的二分查找
        int lo = 0, hi = N - 1;
        while (lo <= hi)
        {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0)
                hi = mid - 1;
            else if (cmp > 0)
                lo = mid + 1;
            else
                return mid;
        }
        return lo;
    }

    public int rank(Key key, int lo, int hi)
    {
        //递归的二分查找
        if (hi < lo)
            return lo;
        int mid = lo + (hi - lo) / 2;
        int cmp = key.compareTo(keys[mid]);
        if (cmp < 0)
            return rank(key, lo, mid-1);
        else if (cmp > 0)
            return rank(key, mid+1, hi);
        else
            return mid;
    }
    public void put(Key key, Value val)
    {
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0)
        {
            vals[i] = val;
            return;
        }
        for (int j = N; j > i; j--)
        {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }
    public Value get(Key key)
    {
        if (isEmpty())
            return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0)
            return vals[i];
        else
            return null;

    }
    public void delete(Key key)
    {

    }
    public Key min()
    {
        return keys[0];
    }
    public Key max()
    {
        return keys[N-1];
    }
    public Key select(int k)
    {
        return keys[k];
    }
    //返回大于等于Key的最小键
    public Key ceiling(Key key)
    {
        if (key == null)
            throw new NullPointerException("argument to ceiling() is null");
        int i = rank(key);
        //sink()在不越界的情况下总指向大于等于Key的最小键,只需判断是否越界
        if (i == N)
            return null;
        else
            return keys[i];
    }
    public Key floor(Key key)
    {
        if (key == null)
            throw new NullPointerException("argument to floor() is null");
        int i = rank(key);
        //考虑Key存在(i),不存在(i-1),不存在且越界
        if (i < N && key.compareTo(keys[i]) == 0)
            return keys[i];
        if (i == 0)
            return null;
        else
            return keys[i-1];
    }
    public boolean contains(Key key)
    {
        return get(key) != null;
    }
    public Iterable<Key> keys()
    {
        return keys(min(), max());
    }
    public Iterable<Key> keys(Key lo, Key hi)
    {
        Queue<Key> queue = new Queue<>();
        for (int i = rank(lo); i < rank(hi); i++)
            queue.enqueue(keys[i]);
        if (contains(hi))
            queue.enqueue(keys[rank(hi)]);
        return queue;
    }

    public static void main(String[] args) {
        int minlen = 8;
        BinarySearchST<String, Integer> st = new BinarySearchST<>(150000);

        File file = new File("/Users/carter/Github/Algorithm/algs4-data/tale.txt");
        In in = new In(file);

        while (!in.isEmpty())
        {
            String word = in.readString();
            if (word.length() < minlen) continue;
            if (!st.contains(word))
                st.put(word, 1);
            else
                st.put(word, st.get(word) + 1);
        }

        String max = " ";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max))
                max = word;
        }
        System.out.println(max + " " + st.get(max));
    }
}
