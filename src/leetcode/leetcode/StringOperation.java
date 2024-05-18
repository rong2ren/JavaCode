package leetcode;

import java.util.HashMap;
import java.util.Map;

public class StringOperation {
    public static void main(String[] args) {
        StringOperation so = new StringOperation();
        so.reverseString(new char[]{'h','e','l','l','o'});
    }

   /**
    * Write a function that reverses a string. The input string is given as an array of characters s.
    You must do this by modifying the input array in-place with O(1) extra memory.
    */
    public void reverseString(char[] s) {
        if(s == null || s.length <= 1) return;
        
        int left = s.length /2 - 1;
        int right;
        if(s.length % 2 == 0){
            //even length
            right = left + 1;
        } else {
            //odd length
            right = left + 2;
        }
        
        while(left >= 0 && right <= s.length){
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            
            left--;
            right++;
        }
    }

    public void reverseString2(char[] s) {
        if(s == null || s.length <= 1) return;

        int left = 0;
        int right = s.length - 1;
        while(left < right){
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;

            left++;
            right--;
        }
    }


    /**     
    * Given two strings s and t, determine if they are isomorphic.
    * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
    * All occurrences of a character must be replaced with another character while preserving the order of characters. 
    * No two characters may map to the same character, but a character may map to itself.
    * We can map a character only to itself or to one other character. (No two characters may map to the same character)
    * "badc" → "baba" → false
    * “foo” → “bar” → false;
    */
    public boolean isIsomorphic(String s, String t) {
        if(s == null || t == null || s.length() != t.length()) return false;
        Map<Character, Character> mappingDictStoT = new HashMap<Character, Character>();
        Map<Character, Character> mappingDictTtoS = new HashMap<Character, Character>();
        
        for(int i = 0; i < t.length(); i++){
            char charS = s.charAt(i);
            char charT = t.charAt(i);
            if(mappingDictStoT.containsKey(charS)){
                //already have a mapping for charS, check if the mapping is charT
                if(mappingDictStoT.get(charS) != charT) return false;
            } else {
                if(mappingDictTtoS.containsKey(charT)){
                    //charT already mapped, so cannot map again. (No two characters may map to the same character)
                    return false;
                } else {
                    mappingDictStoT.put(charS, charT);
                    mappingDictTtoS.put(charT, charS);
                }
            }
        }
        return true;
    }

    public boolean isIsomorphic2(String s, String t) {
        if(s == null || t == null || s.length() != t.length()) return false;
        return transformString(s).equals(transformString(t));
    }

    private String transformString(String s){
        Map<Character, Integer> indexMapping = new HashMap<Character, Integer>();
        StringBuilder strBuilder = new StringBuilder();

        for(int i = 0; i < s.length(); i++){
            char currChar = s.charAt(i);
            if(!indexMapping.containsKey(currChar)){
                indexMapping.put(currChar, i);
            }
            strBuilder.append(Integer.toString(indexMapping.get(currChar)));
            strBuilder.append(" ");
        }

        return strBuilder.toString();
    }

   
 
}
