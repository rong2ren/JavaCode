package datastructure;

import java.util.Stack;

/**
 * for example, push -2, 0, -3 to the stack
 * maintain another stack that holds the mininum when the new number is pushed in.
 * so it will look like, -2, -2, -3
 */
class MinStack {
    private Stack<Integer> val_stack; // the values we added so far
    //space wise, it will be the same size as val_stack, which is not optimal.
    //for example, push in -1, 0, 0, 0, 0, 0, 0 ,,,
    //think of a way to optimize the min_stack size: only need to push in the first -1. since the following 0s do not change the min_value
    private Stack<Integer> min_stack; // the minimum values everytime a new data pushed in

    public MinStack() {
        val_stack = new Stack<>();
        //2nd improvment is using a single value to store the min.
        //everytime push in a node, instead of pushing the actual data, you pushed in a modified data
        //modified data = 2val - previous_min
        //current_min = val
        min_stack = new Stack<>();
    }
    
    public void push(int val) {
        val_stack.push(val);
        if(min_stack.empty()) min_stack.push(val);
        else {
            min_stack.push(Math.min(val, min_stack.peek()));
        }
    }
    
    public void pop() {
        val_stack.pop();
        min_stack.pop();
    }
    
    public int top() {
        return val_stack.peek();
    }
    
    public int getMin() {
        return min_stack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */