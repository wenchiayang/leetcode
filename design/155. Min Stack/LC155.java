import java.util.Stack;
import java.util.Iterator;

/**
 * Naive Thought
 */
public class LC155 {
    private Stack<Integer> stack;

    /** initialize your data structure here. */
    public LC155() {
        stack = new Stack<>();
    }
    
    public void push(int x) {
        stack.push(x);
    }
    
    public void pop() {
        if (!stack.isEmpty()) {
            stack.pop();
        }
    }
    
    public int top() {
        return stack.peek();
    }
    
    // Time complexity: O(n)
    public int getMin() {
        int min = stack.peek();
        Iterator<Integer> iterator = stack.iterator();
        
        while (iterator.hasNext()) {
            min = Math.min(iterator.next(), min);
        }
        
        return min;
    }

    public static void main(String[] args) {
        System.out.println("155. Min Stack [easy][mine]");

        LC155 minStack = new LC155();
        System.out.println("minStack.push(-2)");
        minStack.push(-2);
        System.out.println("minStack.push(0)");
        minStack.push(0);
        System.out.println("minStack.push(-3)");
        minStack.push(-3);
        System.out.println("minStack.getMin() = " + minStack.getMin()); // Returns -3.
        System.out.println("minStack.pop()");
        minStack.pop();
        System.out.println("minStack.top() = " + minStack.top()); // Returns 0.
        System.out.println("minStack.getMin() = " + minStack.getMin()); // Returns -2.
    }
}