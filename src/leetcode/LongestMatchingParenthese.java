package leetcode;

import java.util.Stack;

public class LongestMatchingParenthese {

    public static void main(String[] args) {
        String input = "(()())";
        int longest = longestMatchingParenthese2(input);
        System.out.println("longest matching parenthese of " + input + "=" + longest);
    }

    /*
     * solution 1: using Stack to remember the index of unmatched ‘(’ and ‘)’
     */
    private static int longestMatchingParenthese1(String s){
        if(s == null || s.length() == 0) return 0;

        int longest_length = 0;
        int current_length = 0;
        Stack<Integer> stack = new Stack<>();//store indices of unmatched parentheses ('(' or ')').

        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == ')'){
                if(!stack.isEmpty() && s.charAt(stack.peek()) == '('){
                    //this means this ')' is matched with the top '(' in the stack
                    stack.pop();//pop the matching '('
                    current_length = i - (stack.isEmpty() ? -1 : stack.peek());
                    longest_length = Math.max(longest_length, current_length);
                } else {
                    //the stack is empty
                    //this means that this ')' was unmatched
                    stack.push(i);
                }
            } else {
                //Each time we encounter an opening parenthesis '(' and we dont know if it will be matched
                //push its index onto the stack
                //which represents the index of the last unmatched '('
                stack.push(i);
            }
        }
        return longest_length;
    }

    /*
     * solution 2: using Stack to remember the index of unmatched ‘(’ and ‘)’
     * This **second solution** is also stack-based but uses a **simpler approach** with clever tricks: 

- it pushes an initial value (`-1`) onto the stack to handle unmatched cases more efficiently. -
- The stack stores indices of unmatched parentheses
    - Bottom of the stack: The most recent unmatched ')' or the base index for any upcoming valid parentheses
    - Middle of the stack: unmatched '('
    - Top of the stack: The most recent unmatched '('s waiting to be matched
     */
    private static int longestMatchingParenthese2(String s){
        if (s == null || s.length() == 0) return 0;

        int longest_length = 0;
        // The stack stores indices of unmatched parentheses
        Stack<Integer> stack = new Stack<>();
        // Design: 
        // - Bottom of the stack: The most recent unmatched ')' or the base index for any upcoming valid parentheses
        // - Top of the stack: The most recent unmatched '(' waiting to be matched
        stack.push(-1); //assume each string has a ')' at index '-1' so for potentially valid parenthese, it will starts at 0

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                // Push the index of the unmatched '(' onto the stack, waiting to be potentially matched
                stack.push(i);
            } else {
                // Encountered a ')', pop the top of the stack
                stack.pop();
                if (stack.isEmpty()) {
                    // Case 1: before pop: the stack has only 1 elements, which is the most recent unmatched ')'.
                    // Stack is empty after pop
                    // Push the current ')' index as the new base for future valid substrings
                    stack.push(i);
                } else {
                    // Case 2: before pop: the stack has at least 2 elements. The top of the stack is the most recent unmatched '('. it is now poped and it has been matched by current ')'
                    // Stack is not empty after pop
                    // Valid substring length = current ')' index - top of the stack (most recent unmatched ')')
                    longest_length = Math.max(longest_length, i - stack.peek());
                }
            }
        }
        return longest_length;
    }
}
