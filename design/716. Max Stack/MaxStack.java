import java.util.Stack;

/**
 * Two-stack
 * https://www.youtube.com/watch?v=y7T00suWkrY
 * Time Complexity: O(n) for peekMax(), O(1) for other operations
 * Space Complexity: O(n)
 */
class MaxStack {
    // define 2 stacks
    private Stack<Integer> stack;
    private Stack<Integer> max_stack;

    /** initialize your data structure here. */
    public MaxStack() {
        stack = new Stack<>();
        max_stack = new Stack<>();
    }
    
    public void push(int x) {
        stack.push(x);

        if (max_stack.isEmpty()) {
            max_stack.push(x);
        } else {
            int max = Math.max(x, peekMax());
            max_stack.push(max);
        }
    }
    
    public int pop() {
        max_stack.pop();
        return stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int peekMax() {
        return max_stack.peek();
    }
    
    public int popMax() {
        // find max first
        int max = peekMax();

        // fix stack and max_stack
        // define cache stack to store temp pop out value
        // when stack top item != max_stack top item
        Stack<Integer> cache = new Stack<>();

        while (top() != max) {
            cache.push(pop());
        }

        // when stack top item == max_stack top item
        pop();

        // restore cache items to stack
        while (!cache.isEmpty()) {
            push(cache.pop());
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println("716. Max Stack [easy][2-stack]");

        MaxStack maxStack = new MaxStack();
        System.out.println("maxStack.push(5)");
        maxStack.push(5);
        System.out.println("maxStack.push(1)");
        maxStack.push(1);
        System.out.println("maxStack.push(5)");
        maxStack.push(5);
        System.out.println("maxStack.top() = " + maxStack.top()); // Returns 5.
        System.out.println("maxStack.popMax() = " + maxStack.popMax()); // Returns 5.
        System.out.println("maxStack.top() = " + maxStack.top()); // Returns 1.
        System.out.println("maxStack.peekMax() = " + maxStack.peekMax()); // Returns 5.
        System.out.println("maxStack.pop(): " + maxStack.pop()); // Returns 1.
        System.out.println("maxStack.top() = " + maxStack.top()); // Returns 5.
    }
}
