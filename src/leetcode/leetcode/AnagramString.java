package leetcode;

import java.util.HashMap;
import java.util.Map;

public class AnagramString {
    public static void main(String[] args) {
        System.out.println(isAnagram("anagram", "nagaram"));
    }

    /***
     * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
     * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, 
     * typically using all the original letters exactly once.
     * s: for example ab
     * t: for example a
     * return false
     */
    public static boolean isAnagram(String s, String t) {
        if(s == null || t == null || s.length() != t.length()) return false;

        Map<Character, Integer> charFrequency = new HashMap<Character, Integer>();
        for(int i = 0; i < s.length(); i++){
            char currChar = s.charAt(i);
            charFrequency.put(currChar, charFrequency.getOrDefault(currChar, 0) + 1);
        }
        for(int j = 0; j < t.length(); j++){
            char currChar = t.charAt(j);
            if(charFrequency.get(currChar) == null) return false;
            else {
                if(charFrequency.get(currChar) <= 0) return false;
                else {
                    charFrequency.put(currChar, charFrequency.get(currChar) - 1);
                    if(charFrequency.get(currChar) <= 0) charFrequency.remove(currChar);
                }
            }
        }

        if(charFrequency.size() > 0) return false;
        else return true;
    }


    /**
     * Remember, since only 26 letters, you can use int[26]
     */
    public boolean isAnagram2(String s, String t) {
        if(s == null || t == null || s.length() != t.length()) return false;

        int[] dict = new int[26];
        for(int i = 0; i < s.length(); i++){
            dict[s.charAt(i) - 'a']++;
            dict[t.charAt(i) - 'a']--;
        }
        
        for(int k = 0; k < 26; k++){
            if(dict[k] > 0) return false;
        }
        
        return true;
    }



}
