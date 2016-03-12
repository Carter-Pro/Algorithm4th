package Chapter3;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.introcs.In;

import java.io.File;

/**
 * Created by carter on 16/3/10.
 */
public class BST<Key extends Comparable<Key>, Value> {
    private Node root;
    private class Node
    {
        private Key key;
        private Value val;
        private Node left;
        private Node right;
        private int N;

        public Node(Key key, Value val, int N)
        {
            this.key = key;
            this.val = val;
            this.N = N;     //以该节点为根的子树中的结点总数
        }
    }
    public int size()
    {
        return size(root);
    }
    private int size(Node x)
    {
        if (x == null)
            return 0;
        else
            return x.N;
    }
    public Value get(Key key)
    {
        return get(root, key);
    }
    private Value get(Node x, Key key)
    {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)        return get(x.left, key);
        else if (cmp > 0)   return get(x.right, key);
        else                return x.val;
    }
    public void put(Key key, Value val)
    {
        root = put(root, key, val);
    }
    private Node put(Node x, Key key, Value val)
    {
        if (x == null)
            return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0)   x.left = put(x.left, key, val);
        else if (cmp > 0)   x.right = put(x.right, key, val);
        else                x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
    public boolean contains(Key key) {
        if (key == null)
            throw new NullPointerException("argument to contains() is null");
        return get(key) != null;
    }
    //max min floor ceiling
    public Key max()
    {
        return max(root).key;
    }
    private Node max(Node x)
    {
        if (x.right == null)
            return x;
        else
            return min(x.right);
    }
    public Key min()
    {
        return min(root).key;
    }
    private Node min(Node x)
    {
        if (x.left == null)
            return x;
        else
            return min(x.left);
    }
    public Key floor(Key key)
    {
        return floor(root, key).key;
    }
    private Node floor(Node x, Key key)
    {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0)
            return x;
        if (cmp < 0)
            return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null)
            return t;
        else
            return x;
    }
    //select rank
    public Key select(int k)
    {
        return select(root, k).key;
    }
    private Node select(Node x, int k)
    {
        if (x == null)
            return null;
        int t = size(x.left);
        if (t > k)
            return select(x.left, k);
        else if (t < k)
            return select(x.right, k-t-1);
        else
            return x;
    }
    public int rank(Key key)
    {
        return rank(root, key);
    }
    private int rank(Node x, Key key)
    {
        if (x == null)
            return 0;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0)   return rank(x.left, key);
        else if (cmp > 0)   return 1+ size(x.left) + rank(x.right, key);
        else                return size(x.left);
    }
    //delete delMin delMax
    public void deleteMin()
    {
        deleteMin(root);
    }
    private Node deleteMin(Node x)
    {
        if (x.left == null)
            return x.right;
        x.left = deleteMin(x.left);
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }
    public void deleteMax()
    {
        deleteMax(root);
    }
    private Node deleteMax(Node x)
    {
        if (x.right == null)
            return x.right;
        x.right = deleteMax(x.right);
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }
    public void delete(Key key)
    {
        delete(root, key);
    }
    private Node delete(Node x, Key key)
    {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = delete(x.left, key);
        else if (cmp > 0)
            x.right = delete(x.right, key);
        else
        {
            if (x.left == null)     return x.right;
            if (x.right == null)    return x.left;
            Node t = x;
            x = min(x.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }
    //keys
    public Iterable<Key> keys()
    {
        return keys(min(), max());
    }
    public Iterable<Key> keys(Key lo, Key hi)
    {
        Queue<Key> queue = new Queue<>();
        Keys(root, queue, lo, hi);
        return queue;
    }
    private void Keys(Node x, Queue<Key> queue, Key lo, Key hi)
    {
        if (x == null)
            return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0)
            Keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >=0)
            queue.enqueue(x.key);
        if (cmphi > 0)
            Keys(x.right, queue, lo, hi);
    }
    public static void main(String[] args) {
        int minlen = 10;
        BST<String, Integer> st = new BST<>();

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
