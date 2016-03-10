package Chapter1;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;

import java.io.File;
import java.util.Iterator;


/**
 * Created by carter on 16/3/8.
 */
public class Queue<Item> implements Iterable<Item>{
    private class Node {
        Item item;
        Node next;
    }
    private int N = 0;
    private Node first;
    private Node last;
    public boolean isEmpty() { return N == 0;}
    public int size() { return  N; }
    public void enqueue(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        N++;
    }
    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        N--;
        return item;
    }
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }
    private class QueueIterator implements Iterator<Item> {
        Node current = first;
        public boolean hasNext() {
            return current != null;
        }
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    public static void main(String[] args) {
        Queue<String> queue = new Queue<>();
        File file = new File("/Users/carter/Github/Algorithm/algs4-data/tobe.txt");
        In in = new In(file);
        while (!in.isEmpty())
        {
            String item = in.readString();
            if (!item.equals("-"))
                queue.enqueue(item);
            else
                if (!queue.isEmpty())
                    StdOut.print(queue.dequeue() + " ");
        }
        System.out.println("(" + queue.size() + " left on queue)");
    }
}
