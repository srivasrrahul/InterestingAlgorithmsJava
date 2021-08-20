package minstack;

import java.util.Stack;

class MinStack {

    /** initialize your data structure here. */
    Stack<Integer> values = new Stack<>();
    Stack<Integer> minValue = new Stack<>();
    public MinStack() {

    }

    public void push(int val) {
        values.push(val);
        if (minValue.isEmpty()) {
            minValue.push(val);
        }else {
            int currentMin  = minValue.peek();
            if (val <= currentMin) {
                minValue.push(val);
            }
        }
    }

    public void pop() {
        if (values.isEmpty() == false) {
            int top = values.peek();
            if (minValue.peek() == top) {
                minValue.pop();
            }

            values.pop();
        }
    }

    public int top() {
        return values.peek();
    }

    public int getMin() {
        return minValue.peek();
    }
}
