package Chapter3;

/**
 * Created by carter on 16/3/12.
 */
public class RedBlackBST<Key extends Comparable<Key>, Value>
{
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    private class Node
    {
        Key key;
        Value val;
        int N;
        Node left;
        Node right;
        boolean color;

        Node(Key key, Value val, int N, boolean color)
        {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }

    private boolean isRed(Node h)
    {
        return h.color == RED;
    }
    private Node rotateLeft(Node h)
    {
        Node x = h.left;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }
    private Node rotateRight(Node h)
    {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left)
                + size(h.right);
        return x;
    }
    public int size()
    {
        return size(root);
    }
    private int size(Node x)
    {
        if (x == null)
            return 0;
        return x.N;
    }
    private void flipColors(Node h)
    {
        if (h == null)
            return;
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }
    public void put(Key key, Value val)
    {
        root = put(root, key, val);
        //the root node is always black.
        root.color = BLACK;
    }
    private Node put(Node h, Key key, Value val)
    {
        if (h == null)
            return new Node(key, val, 1, RED);
        int cmp = key.compareTo(h.key);
        if      (cmp < 0)   h.left = put(h.left, key, val);
        else if (cmp > 0)   h.right = put(h.right, key, val);
        else                h.val = val;

        if (!isRed(h.left) && isRed(h.right))       h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left))    h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))        flipColors(h);

        h.N = 1 + size(h.left) + size(h.right);
        return h;
    }
    public Value get(Key key)
    {

    }
    public Node get(Key key)
    {

    }
    //delete delMin delMax
    public void delete(Key key)
    {

    }
    private Node delete(Node h, Key key)
    {

    }
    public void delMin(Key key)
    {
        if ()
    }
    private Node delMin(Node h, Key key)
    {

    }
    private Node moveRedLeft(Node h)
    {

    }
    private Node balance(Node h)
    {

    }
    public void delMax(Key key)
    {

    }
    private Node delMax(Node h, Key key)
    {

    }

}
