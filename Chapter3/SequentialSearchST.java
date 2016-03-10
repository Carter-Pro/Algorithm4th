package Chapter3;


import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.introcs.In;

import java.io.File;

/**
 * Created by carter on 16/3/10.
 */
public class SequentialSearchST<Key, Value> {
    private Node first;
    private int N = 0;
    private class Node
    {
        Key key;
        Value val;
        Node next;
        public Node(Key key, Value val, Node next)
        {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Value get(Key key)
    {
        //查找给定的键,返回相关联的值
        if (key == null)
            throw new NullPointerException("argument to get() is null");
        for (Node x = first; x != null; x= x.next)
            if (x.key.equals(key))
                return x.val;
        N--;
        return null;
    }
    public void put(Key key, Value val)
    {
        //查找给定的键,找到则更新其值,否则在表中新建节点
        if (key == null)
            throw new NullPointerException("argument to put() is null");
        for (Node x = first; x != null; x = x.next)
            if (x.key.equals(key))
            {
                x.val = val;
                return;
            }
        first = new Node(key, val, first);
        N++;
    }
    public int size()
    {
        return N;
    }
    public boolean isEmpty()
    {
        return N == 0;
    }
    //即时型,递归
    public void delete(Key key)
    {
        if (key == null)
            throw new NullPointerException("argument to delete() is null");
        first = delete(first, key);
    }
    private Node delete(Node x, Key key)
    {
        if (x == null)
            return null;
        if (key.equals(x.key))
        {
            N--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }
    public boolean contains(Key key)
    {
        if (key == null)
            throw new NullPointerException("argument to contains() is null");
        return get(key) != null;
    }
    public Iterable<Key> keys()
    {
        Queue<Key> queue = new Queue<>();
        for (Node x = first; x != null; x = x.next)
            queue.enqueue(x.key);
        return queue;
    }
    public static void main(String[] args) {
        int minlen = 10;
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();

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
