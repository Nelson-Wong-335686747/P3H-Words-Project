import java.util.Iterator;

public class Stack<E> implements Iterable<E>
{
    private Node first = null; 
    private int size = 0;
    
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
        size++;
    }
    
    public E pop() {
        E item = first.item;
        first = first.next;
        size--;
        return item;
    }
    
    public int getSize()
    {
        return size;
    }

    public E peek()
    {
        return first.item;
    }
    
    public String popAll()
    {
        String str = "";
        while(!isEmpty())
        {
            str = pop() + str;
        }
        return str;
    }
    // To make this data structure work with for-each loops
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    class MyIterator implements Iterator<E> {

        private Node n = first;

        public boolean hasNext() {
            return n != null;
        }

        public E next() {
            E item = n.item;
            n = n.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}