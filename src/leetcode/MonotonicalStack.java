package leetcode;

import java.util.Stack;

public class MonotonicalStack {


    public String decodeString(String s) {
        if(s == null || s.length() == 0) return "";

        Stack<String> stack = new Stack<>();//push previous string, current k for current string
        StringBuilder builder = new StringBuilder();
        int k = 0;

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);

            if(c == '['){
                //start of the new encoded string between [ and ]
                //push the previous string and current k to the stack
                stack.push(builder.toString());//previous string
                stack.push(String.valueOf(k));//current k for the upcoming encoded string
                //reset current string
                builder.setLength(0);
                //reset k in case of something like 3[a2[c]]
                k = 0;
            } else if(c == ']'){
                //end of the current encoded string
                //pop from the stack of current k
                int current_k = Integer.parseInt(stack.pop());
                String current_string = builder.toString();
                while(current_k > 1){
                    builder.append(current_string);
                    current_k--;
                }
                //pop from the stack of previous string
                String previous_string = stack.pop();
                builder.insert(0, previous_string);
            } else if(c >= '0' && c <= '9'){
                //numbers - k
                k = k * 10 + (c - '0');
            } else {
                //building the current string between [ and ]
                builder.append(c);
            }
        }

        return builder.toString();
    }

    

}


