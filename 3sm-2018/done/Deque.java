import java.util.Iterator;
import java.util.NoSuchElementException;
// Artur M R dos Santos - 10297734
// Foram utilizadas referencias ao material de Princeton
// e partes dos códigos de lá.
public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int n;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> previous;
    }

    // Construtor
    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    // Métodos
    public boolean isEmpty() {
        return n == 0;
    }

    public int size(){
        return n;
    }

    public void addFirst(Item item){
        if(item == null) throw new IllegalArgumentException();
        Node<Item> added = new Node<Item>();
        added.item = item;
        if (isEmpty()) {
            first = added;
        }
        else if (first != null){
            first.previous = added;
            added.next = first;
        }
        else {
            first = added;
            last.previous = first;
        }
        added = first;
        n++;
    }

    public void addLast(Item item) {
        if(item == null) throw new IllegalArgumentException();
        Node<Item> added = new Node<Item>();
        added.item = item;
        if (isEmpty()) {
            first = added;
        }
        else if (last != null){
            last.next = added;
            added.previous = last;
        }
        else {
            last = added;
            first.next = last;
        }
        added = last;
        n++;
    }

    public Item removeFirst(){
        if (isEmpty()) throw new NoSuchElementException();
        Node<Item> oldfirst = first;
        first = first.next;
        Item item = oldfirst.item;
        if (first == null) last = null;
        else {
            first.previous = null;
        }
        System.out.println(first + " - - - ");
        n--;
        return item;
    }

    public Item removeLast(){
        if (isEmpty()) throw new NoSuchElementException();
        Node<Item> oldlast = last;
        if (first == last) {
            Item item = first.item;
            last = null;
            return item;
        }
        else {
            last = last.previous;
        }
        if (last == null) first = null;
        else {
            last.next = null;
        }
        Item item = oldlast.item;
        n--;
        return item;
    }

    // Iterador
    public Iterator<Item> iterator(){
        return new DequeIterator<Item>(first);
    }

    private class DequeIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public DequeIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;  }

        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String args[]){
        System.out.println("Inicializing Deque...");
        System.out.println("...");
        Deque<String> doubleQueue = new Deque<String>();
        System.out.println("...");
        System.out.println("Adding to the begin: hi");
        doubleQueue.addFirst("hi");
        System.out.println("Adding to the end: bye");
        doubleQueue.addLast("bye");
        int size = doubleQueue.size();
        System.out.println("Size: " + size);
        System.out.println("Removing hi...");
        doubleQueue.removeFirst();
        System.out.println("Adding to the begin: hello");
        doubleQueue.addFirst("hello");
        doubleQueue.removeLast();
        System.out.println("Removing bye...");
        doubleQueue.addLast("How are you?");

        for (String s: doubleQueue) {
            System.out.println(s);
        }
    }
}