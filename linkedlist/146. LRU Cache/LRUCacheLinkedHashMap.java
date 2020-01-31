import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Solution
 * Ordered Dictionary(LinkedHashMap class)
 * https://leetcode.com/problems/lru-cache/solution/
 * Time Complexity: O(1)
 * Space Complexity: O(n)
 */
public class LRUCacheLinkedHashMap extends LinkedHashMap<Integer, Integer> {
    private static final long serialVersionUID = 1L;
    private int capacity;

    public LRUCacheLinkedHashMap(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }
    
    public int get(int key) {
        return super.getOrDefault(key, -1);
    }
    
    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > capacity;
    }

    public static void main(String[] args) {
        System.out.println("146. LRU Cache [medium]");
        System.out.println("Using LinkedHashMap");
        System.out.println("Example:");

        LRUCacheLinkedHashMap cache = new LRUCacheLinkedHashMap( 2 /* capacity */ );
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