package Chapter1;

import java.util.Iterator;

/**
 * Created by carter on 16/3/8.
 */
public class Stack<Item> implements Iterable<Item>{
    private class Node {
        Item item;
        Node next;
    }
    private int N = 0;
    private Node first;
    public boolean isEmpty() {  return N == 0;  }
    public int size() { return N; }
    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }
    public Item pop() {
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }
    public Iterator<Item> iterator() {
        return new StackIterator();
    }
    private class StackIterator implements Iterator<Item>{
        Node current = first;
        public boolean hasNext() {  return N != 0; }
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("gu");
        stack.push("lei");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
