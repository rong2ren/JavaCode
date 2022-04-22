package leetcode;

import java.util.HashSet;
import java.util.Set;

public class LongestSubStringNoRepeat {

    public static void main(String[] args) {
        LongestSubStringNoRepeat lssnp = new LongestSubStringNoRepeat();
        System.out.println("length:" + lssnp.lengthOfLongestSubstring2("bbbbb"));
    }
    /*
     * brute force: find all substring, and check if the substring contains any duplicate characters.
     */
    public int lengthOfLongestSubstring(String s) {
        if((s == null) || s.length() == 0) return 0;

        int output = 1; //the default value is only character - length = 1
        
        for(int i = 0; i < s.length(); i++){
            for (int j = i + 1; j <= s.length(); j++){
                if(!stringContainsRepeats(s.substring(i,j))){
                    //System.out.println(s.substring(i, j));
                    if(j - i > output) output = j - i;
                }
                
            }
        }
        
        return output;
    }

    private boolean stringContainsRepeats(String s){
        if(s == null || s.length() <= 0) return false;
        if(s.length() == 1) return true;

        Set<Character> charSet = new HashSet<>();
        for (int i = 0; i < s.length(); i++){
            if(!charSet.add(s.charAt(i))) return true;
        }
        return false;
    }


    /**
     * 
     * 2nd solution: StringBuilder
     * 
     */
    public int lengthOfLongestSubstring2(String s) {
        if(s == null || s.length() <= 0) return 0;
        if(s.length() == 1) return 1;

        int output = 1;
        StringBuilder strBuilder = new StringBuilder();
        int beginIndex = 0;
        int endIndex = -1;

        for(int i = 0; i < s.length(); i++){
            //get the index of the character
            int charIndex = strBuilder.indexOf(s.substring(i, i+1));
            if(charIndex == -1){
                //not in the stringBuilder, need to append to the string and move the endIndex right
                strBuilder.append(s.charAt(i));
                endIndex++;
            } else {
                //if alreaady in the StringBuilder, remove the previous 
                if (endIndex - beginIndex + 1 > output) output = endIndex - beginIndex + 1;
                strBuilder.delete(0, charIndex + 1);
                //if(charIndex > 0) strBuilder.delete(0, charIndex);
                //else strBuilder.deleteCharAt(charIndex);
                beginIndex = beginIndex + charIndex + 1;
                strBuilder.append(s.charAt(i));
                endIndex = i;
            }
        }
        if (endIndex - beginIndex + 1 > output) output = endIndex - beginIndex + 1;
        return output;
    }

    /**
     * 
     * 3rd solution: two pointers, sliding window, hashSet
     * go through whole character in the string, and maintain the hashset
     * 
     */
    public int lengthOfLongestSubstring3(String s) {
        if(s == null || s.length() <= 0) return 0;
        if(s.length() == 1) return 1;

        int output = 1;

        Set<Character> charSet = new HashSet<Character>();

        int beginIndex = 0;
        int endIndex = 0;//endIndex pointer will go through each character
        while(endIndex < s.length()){
            if(!charSet.contains(s.charAt(endIndex))){
                //new character
                charSet.add(s.charAt(endIndex));
                endIndex++;
                output = Math.max(charSet.size(), output);
            } else {
                //duplicate, update the sliding window
                charSet.remove(s.charAt(beginIndex));
                beginIndex++;
            }
        }
        
        return output;
    }

    /**
     * 
     * 4th solution: two pointers, sliding window, hashSet
     * go through whole character in the string, and maintain the hashset
     * same solution as the 3rd one but differernt writing
     */
    public int lengthOfLongestSubstring4(String s) {
        if(s == null || s.length() <= 0) return 0;
        if(s.length() == 1) return 1;

        int output = 1;

        Set<Character> charSet = new HashSet<Character>();

        int beginIndex = 0;
        int endIndex = 0;//endIndex pointer will go through each character
        for (endIndex = 0; endIndex < s.length(); endIndex++){
            char theCharToCheck = s.charAt(endIndex);
            while(charSet.contains(theCharToCheck)){
                charSet.remove(s.charAt(beginIndex));
                beginIndex++;
            }
            
            charSet.add(theCharToCheck);
            output = Math.max(output, charSet.size());
        }
        
        return output;
    }

}
