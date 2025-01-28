package Complex;

import java.util.Stack;

public class ObfuscateMessage {
/*
 * Amazon developers are creating a new security module for Amazon Web Services. One of its functions is to obfuscate incoming messages. The obfuscation method is to select some substring of the message and right rotate that substring by 1 position one time only. The substring is selected in such a way that the resulting obfuscated message is alphabetically the smallest message that can be formed using this operation.

Here, right rotating any string by 1 position is to shift every character of string a in the right direction by 1 position and remove the last character and append it to the beginning of string a. For example, a = "ahssd", after right rotation by 1 position string a changes to "dahss".

Given the input message as a string, find the obfuscated message.

Note: A string x is alphabetically smaller than string y, if either x is a prefix of y (and x ≠ y), or there exists such i (0 ≤ i < min(|x|, |y|)), that x[i] < y[i], and for any j (0 ≤ j < i) x[j] = y[j]. Here, |a| denotes the length of the string a.
    */
    public static void main(String[] args) {
        System.out.println(findObfuscateMessage2("aahhaab"));
    }

    public static String findObfuscateMessage(String message) {
        //find a substring of the message that are decreasing first and then increasing
        char[] characters = message.toCharArray();
        int window_start = -1, window_end = -1;
        for(int i = 1; i < characters.length; i++){
            if(characters[i] < characters[i-1]) {
                window_start = i - 1;
                while(characters[window_start - 1] == characters[window_start]) window_start--;
            }
            else if(window_start > -1 && characters[i] > characters[i-1]){
                window_end = i - 1;
                break;
            }
        }
        System.out.println("window=" + window_start + "-" + window_end);
        System.out.println("roate substring:" + message.substring(window_start, window_end + 1));
        return null;
    }

    public static String findObfuscateMessage2(String message) {
        //find a substring of the message that are decreasing first and then increasing
        char[] characters = message.toCharArray();
        int start = -1, end = -1;

        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < characters.length; i++){
            while(!stack.isEmpty() && characters[i] < characters[stack.peek()]){
                //smaller character
                if(end == -1) end = i;
                start = stack.pop();
                //System.out.println("potential start:" + characters[start] + "@" + start);
            } 
            stack.push(i);
        }
        System.out.println("roate substring:" + message.substring(start, end + 1));
        //doing rotate
        char end_char = characters[end];
        for(int i = start + 1; i <= end; i++){
            characters[i] = characters[i-1];
        }
        characters[start] = end_char;
        return String.valueOf(characters);
    }
}
