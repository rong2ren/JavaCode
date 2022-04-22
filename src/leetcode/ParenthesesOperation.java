package leetcode;

import java.util.Stack;

public class ParenthesesOperation {
    

    /**
     * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', 
     * determine if the input string is valid.
     * 
     * An input string is valid if:
     * Open brackets must be closed by the same type of brackets.
     * Open brackets must be closed in the correct order.
     */
    public boolean isValid(String s) {
        Stack<Character> charStack = new Stack<Character>();
        for(int i = 0; i < s.length(); i++){
            char currChar = s.charAt(i);
            if(currChar == '(' || currChar == '{' || currChar == '[') charStack.push(currChar);
            else {
                if(charStack.isEmpty()) return false;
                
                if(currChar == ')' && charStack.peek() == '(') charStack.pop();
                else if(currChar == '}' && charStack.peek() == '{') charStack.pop();
                else if(currChar == ']' && charStack.peek() == '[') charStack.pop();
                else return false;
            }
        }
        return charStack.isEmpty();

    }
}
