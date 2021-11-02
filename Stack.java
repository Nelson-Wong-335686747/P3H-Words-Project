import java.util.Iterator;
import java.util.*;


public class Stack<E> 
{
    private Node first = null;
    
    private class Node {
        private E item;
        private Node next;
    } 
    
    public boolean isEmpty() {
        return first == null;
    }
    
    public void push(E item) {
        Node second = first;
        first = new Node();
        first.item = item;
        first.next = second;
    }
    
    public E pop() {
        E item = first.item;
        first = first.next;
        return item;
    }
}