package Chapter1;

import java.util.Iterator;

/**
 * Created by carter on 16/3/8.
 */
public class Bag<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node next;
    }
    private Node first;
    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }
    public Iterator<Item> iterator() {
        return new BagIterator();
    }
    private class BagIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() {
            return current != null;
        }
        public Item next() {
            Item item = current.item;
            first = first.next;
            return item;
        }
    }
}
