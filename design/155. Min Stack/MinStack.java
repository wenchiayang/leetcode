import java.util.Stack;

/**
 * Two-Stack Solution
 * https://www.youtube.com/watch?v=WxCuL3jleUA
 */
class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> min_stack;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        min_stack = new Stack<>();
    }
    
    public void push(int x) {
        stack.push(x);
        
        if (min_stack.isEmpty() || x <= min_stack.peek()) {
            min_stack.push(x);
        }
    }
    
    public void pop() {
        int value = stack.pop();
        
        if (value == min_stack.peek()) {
            min_stack.pop();
        }
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return min_stack.peek();
    }

    public static void main(String[] args) {
        System.out.println("155. Min Stack [easy][2-stack]");

        MinStack minStack = new MinStack();
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