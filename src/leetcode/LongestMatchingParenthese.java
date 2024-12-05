package leetcode;

import java.util.Stack;

public class LongestMatchingParenthese {

    public static void main(String[] args) {
        String input = "(()())";
    }

    private static int longestMatchingParenthese(String s){
        if(s == null || s.length() == 0) return 0;

        int longest_length = 0;
        Stack<Integer> stack = new Stack<>();//put ( index
        int last_unmatched = -1;//last unmatched ('s index
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == ')'){

            }
        }


        return longest_length;
    }
}
