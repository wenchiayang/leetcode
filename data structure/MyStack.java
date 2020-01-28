import java.util.LinkedList;

/**
 * MyStack(LIFO, FILO)
 */
public class MyStack<E> {
    private LinkedList<E> list = new LinkedList<>();
    public MyStack() {}
    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void push(E e) {
        list.addFirst(e);
    }

    public E pop() {
        if (isEmpty()) {
            return null;
        }

        return list.removeFirst();
    }
    
    public E peek() {
        return list.getFirst();
    }

    public static void main(String[] args) {
        MyStack<String> myStack = new MyStack<>();

        for (int i = 1; i <= 3; i++) {
            System.out.println("push person" + i);
            myStack.push("person " + i);
        }

        for (int i = 1; i <= 4; i++) {
            System.out.println("pop '" + myStack.pop() + "'");
        }
    }
}