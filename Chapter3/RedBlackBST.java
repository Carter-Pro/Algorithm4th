package Chapter3;

import java.util.NoSuchElementException;

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
        if (h == null)
            return false;
        return h.color == RED;
    }
    private Node rotateLeft(Node h)
    {
        Node x = h.right;
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
    //如果是2-3-4树则向下和向上的过程都进行变换
    private Node put(Node h, Key key, Value val)
    {
        //向下的过程中只比较
        if (h == null)
            return new Node(key, val, 1, RED);
        int cmp = key.compareTo(h.key);
        if      (cmp < 0)   h.left = put(h.left, key, val);
        else if (cmp > 0)   h.right = put(h.right, key, val);
        else                h.val = val;
        //向上的过程中进行变换保持红黑树的平衡性
        if (!isRed(h.left) && isRed(h.right))       h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left))    h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))        flipColors(h);

        h.N = 1 + size(h.left) + size(h.right);
        return h;
    }
    public Value get(Key key)
    {
        if (key == null)
            throw new NullPointerException("argument to get() is null");
        return get(root, key);
    }
    public Value get(Node x, Key key)
    {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else              return x.val;
        }
        return null;
    }
    //删除操作
    //删除的过程中为了保证平衡性,因此确保当前结点不是2-结点
    //1.根结点可能有两种情况:
    //  (1)根结点是2-,并且它的两个子结点都是2-.将其变成一个4-
    //  (2)否则,需要保证左子节点不是2-,不然是要从右子结点借个键
    //delete delMin delMax
    public void delete(Key key)
    {
        if (isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = delete(root, key);
        if (!isEmpty())
            root.color = BLACK;
    }
    private Node delete(Node h, Key key)
    {
        if (key.compareTo(h.key) < 0)  {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        }
        else {
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                Node x = min(h.right);
                h.key = x.key;
                h.val = x.val;
                h.right = deleteMin(h.right);
            }
            else h.right = delete(h.right, key);
        }
        return balance(h);
    }
    public void deleteMin()
    {
        if (isEmpty())
            throw new NoSuchElementException("BST nuderflow");

        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMin(root);
        if (!isEmpty())
            root.color = BLACK;
    }
    private Node deleteMin(Node h)
    {
        if (h.left == null)
            return null;
        //判断h的左子结点是否是2-结点
        if (!isRed(h.left) && !isRed(h.left.left))
        {
            h = moveRedLeft(h.left);
        }
        h.left = deleteMin(h.left);
        return balance(h);
    }
    private Node moveRedLeft(Node h)
    {
        //假设结点h是为红色,h.left和h.left.left都是黑色
        //将h.left或者h.left的子节点之一变红
        flipColors(h);
        //左子结点的兄弟结点是否是2-结点,不是则需要转换
        if (isRed(h.right.left))
        {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }
    //分解向下过程中的临时4-节点
    private Node balance(Node h)
    {
        if (isRed(h.right))
            h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left))
            h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))
            flipColors(h);

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }
    public void deleteMax()
    {
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMax(root);
        if (!isEmpty())
            root.color = BLACK;
    }
    private Node deleteMax(Node h)
    {

        if (isRed(h.left))
            h = rotateRight(h);
        if (h.right == null)
            return null;
        //判断当前结点的右子结点是否是2-结点
        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);
        h.right = deleteMax(h.right);
        return balance(h);
    }
    private Node moveRedRight(Node h)
    {
        flipColors(h);
        //判断当前结点的左子结点是否是2-结点
        if (!isRed(h.left.left))
        {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }
    public boolean isEmpty()
    {
        return root == null;
    }
    public Key min() {
        if (isEmpty())
            throw new NoSuchElementException("called min() with empty symbol table");
        return min(root).key;
    }
    private Node min(Node x) {
        if (x.left == null)
            return x;
        else
            return min(x.left);
    }
    public Key max() {
        if (isEmpty())
            throw new NoSuchElementException("called max() with empty symbol table");
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null)
            return x;
        else
            return max(x.right);
    }

}
