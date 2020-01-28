/**
 * Implementation
 * https://www.youtube.com/watch?v=uijdYLYAc1U
 */
public class SinglyLinkedList<T> {
    /**
     * Node class
     * @param <T>
     */
    private static class Node<T> {
        private T element;
        private Node<T> next;
        public Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }

        /**
         * @return the element
         */
        public T getElement() {
            return element;
        }

        /**
         * @return the next
         */
        public Node<T> getNext() {
            return next;
        }

        /**
         * @param next the next to set
         */
        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
    
    // List Implementation
    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;
    
    public SinglyLinkedList() {}

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T first() {
        if (isEmpty()) {
            return null;
        }

        return head.getElement();
    }

    public T last() {
        if (isEmpty()) {
            return null;
        }

        return tail.getElement();
    }

    public void addFirst(T element) {
        head = new Node<>(element, head);

        if (size == 0) {
            tail = head;
        }

        size++;
        System.out.println("Added head node with ''" + head.getElement() + "'' element.");
    }

    public void addLast(T element) {
        Node<T> newNode = new Node<>(element, null);

        if (isEmpty()) {
            head = newNode;
        } else {
            tail.setNext(newNode);
        }

        tail = newNode;
        size++;
        System.out.println("Added tail node with '" + tail.getElement() + "' element.");
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        T firstNode = head.getElement();
        head = head.getNext();
        size--;

        if (size == 0) {
            tail = null;
        }

        System.out.println("Removed head node with '" + firstNode + "' element.");

        return firstNode;
    }

    public void reverseList() {
        if (size <= 1) {
            return;
        } else if (size == 2) {
            tail.setNext(head);
            head = tail;
            tail = head.getNext();
            tail.setNext(null);
        } else {
            Node<T> curr = head;
            Node<T> currNext = head.getNext();
            Node<T> currNextNext = currNext.getNext();
            tail = head;
            while (currNext != null) {
                currNext.setNext(curr);
                curr = currNext;
                currNext = currNextNext;
                if (currNextNext !=  null) {
                    currNextNext = currNextNext.getNext();
                }
            }
            tail.setNext(null);
            head = curr;
        }
    }

    public T removeElement(T element) {
        Node<T> curr = head;
        Node<T> prev = head;
        int position = 0;

        while (curr != null && !curr.getElement().equals(element)) {
            prev = curr;
            curr = curr.getNext();
            position++;
        }

        if (curr == null) {
            return null;
        } else {
            if (head == curr) {
                head = curr.getNext();
            } else if (tail == curr) {
                tail = prev;
                tail.setNext(null);
            } else {
                prev.setNext(curr.getNext());
            }

            System.out.println("Found and removed node at position " + position);
            size--;
            return curr.getElement();
        }
    }

    public static void main(String[] args) {
        // SinglyLinkedList<String> myList = new SinglyLinkedList<>();
        SinglyLinkedList<Integer> myList = new SinglyLinkedList<>();
        
        for (int i = 1; i <= 6; i++) {
            if (i <= 3) {
                myList.addFirst(i);
                // myList.addFirst("element " + i);
            } else {
                myList.addLast(i);
                // myList.addLast("element " + i);
            }
        }

        myList.reverseList();
        myList.removeElement(4);
        // myList.removeElement("element 4");
        while (!myList.isEmpty()) {
            myList.removeFirst();
        }
    }
}