import java.util.LinkedList;

/**
 * MyQueue(FIFO)
 */
public class MyQueue<E> {
    private LinkedList<E> list = new LinkedList<>();
    public MyQueue() {}
    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void enqueue(E e) {
        list.addLast(e);
    }

    public E dequeue() {
        if (isEmpty()) {
            return null;
        }

        return list.removeFirst();
    }
    
    public E first() {
        return list.getFirst();
    }

    public static void main(String[] args) {
        MyQueue<String> myQueue = new MyQueue<>();
        for (int i = 1; i <= 5; i++) {
            System.out.println("enqueue person" + i);
            myQueue.enqueue("person " + i);
        }

        for (int i = 1; i <= 6; i++) {
            System.out.println("dequeue '" + myQueue.dequeue() + "'");
        }
    }
}