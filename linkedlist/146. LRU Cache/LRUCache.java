import java.util.HashMap;
import java.util.Map;

/**
 * Solution
 * Hashmap + DoubleLinkedList
 * https://www.youtube.com/watch?v=NDpwj0VWz1U
 * Time Complexity: O(1)
 * Space Complexity: O(n)
 */
public class LRUCache {
    // private properties
    private int capacity;
    private Map<Integer, DNode> cache;
    private DNode head = new DNode();
    private DNode tail = new DNode();

    public LRUCache(int capacity) {
        cache = new HashMap<>(capacity);
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        int result = -1;
        DNode target = cache.get(key);

        if (target != null) {
            result = target.value;
            moveToHead(target);
        }

        return result;
    }
    
    public void put(int key, int value) {
        DNode node = cache.get(key);

        if (node != null) {
            node.value = value;
            moveToHead(node);
        } else {
            if (cache.size() == capacity) {
                cache.remove(tail.prev.key);
                removeNode(tail.prev);
            }

            DNode newNode = new DNode();
            newNode.key = key;
            newNode.value = value;

            cache.put(key, newNode);
            addNode(newNode);
        }
    }

    // Doubly-Linkedlist operations
    // always add new node right after head
    private void addNode(DNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DNode node) {
        DNode prev = node.prev;
        DNode next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    private void moveToHead(DNode node) {
        removeNode(node);
        addNode(node);
    }

    /**
     * DNode class
     */
    class DNode {
        int key;
        int value;
        DNode prev;
        DNode next;
    }
    
    public static void main(String[] args) {
        System.out.println("146. LRU Cache [medium]");
        System.out.println("Using Hashmap + DoubleLinkedList");
        System.out.println("Example:");

        LRUCache cache = new LRUCache( 2 /* capacity */ );
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1)); // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2)); // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        System.out.println(cache.get(1)); // returns -1 (not found)
        System.out.println(cache.get(3)); // returns 3
        System.out.println(cache.get(4)); // returns 4
    }
}