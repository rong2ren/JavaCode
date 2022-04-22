package leetcode;

import java.util.Map;
import java.util.HashMap;

public class LongestSubString {

    public static void main(String[] args) {
        System.out.println(characterReplacement2("ABAB", 2));
    }
    
    /**
     * 
     * You are given a string s and an integer k. 
     * You can choose any character of the string and change it to any other uppercase English character. 
     * You can perform this operation at most k times.
     * Return the length of the longest substring containing the same letter you can get after performing the above operations.
     * 
     * Input: s = "ABAB", k = 2
     * Output: 4
     * Explanation: Replace the two 'A's with two 'B's or vice versa.
     * 
     * Input: s = "AABABBA", k = 1
     * Output: 4
     * Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
     * The substring "BBBB" has the longest repeating letters, which is 4.
     * 
     * Input: s = "ABBB", k = 2
     * Output: 4
     * Explanation: Replace the 'A' to 'B'
     * 
     * Sliding window
     * Need to replace the less frequent character inside the window
     */
    public static int characterReplacement(String s, int totalReplacementAllowed) {
        if(s == null || s.length() <= 0) return 0;
        if(s.length() == 1) return 1;

        int maxLength = Integer.MIN_VALUE;
        int windowStart = 0;
        int maxFrequency = Integer.MIN_VALUE;

        Map<Character, Integer> charCountMap = new HashMap<Character, Integer>();
        for(int windowEnd = 0; windowEnd < s.length(); windowEnd++){
            char rightChar = s.charAt(windowEnd);
            //put the character in the map, increase the value if it is already in the hashmap
            charCountMap.put(rightChar, charCountMap.getOrDefault(rightChar, 0) + 1);
            maxFrequency = Math.max(maxFrequency, charCountMap.get(rightChar));
            //the size of the window - count of the most frequent character in the window <= k
            //if above is true, then the window is valid, then shift the windowEnd
            //only when the window is not valid, shift the windowStart
            while(windowEnd - windowStart + 1 - maxFrequency > totalReplacementAllowed){
                char leftChar = s.charAt(windowStart);
                charCountMap.put(leftChar, charCountMap.get(leftChar) - 1);
                if(charCountMap.get(leftChar) == 0) {
                    charCountMap.remove(leftChar);
                }
                windowStart++;
            }
            //if the window is valid, we will update the maxLength
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }


        return maxLength;
    }



    /**
     * solution 2:
     * use an array, since it has 26 characters
     * We can use the slide window to go through the string and find out the answer.
     *Take “s= AAABBABB, k = 1” for example:
     *Max window size is valid letter repeating length plus k length
     *A AABBABB => max window size is 2
     *AA ABBABB => max window size is 3
     *AAA BBABB => max window size is 4
     *AAAB BABB => max window size is 4
     *AAABB ABB => max window size is 4 => A AABB ABB
     *A AABBA BB => max window size is 4 => AA ABBA BB
     *AA ABBAB B => max window size is 4 => AAA BBAB B
     *AAA BBABB => max window size is 5
     */
    public static int characterReplacement2(String s, int k) {
        if(s == null || s.length() <= 0) return 0;
        if(s.length() == 1) return 1;

        int maxRepeatingCharCount = 0;
        int maxLength = Integer.MIN_VALUE;
        int[] charCount = new int[26];
        int windowStart = 0;
        for(int windowEnd = 0; windowEnd < s.length(); windowEnd++){
            //calculate current character's repeating count
            int currentCharCount = ++charCount[s.charAt(windowEnd) - 'A'];
            //maxRepeatingCharCount store the maximum repeating character count so far
            maxRepeatingCharCount = Math.max(maxRepeatingCharCount, currentCharCount);
            int maxWindowLength = k + maxRepeatingCharCount;
            if(windowEnd - windowStart + 1 > maxWindowLength){
                charCount[s.charAt(windowStart) - 'A']--;
                windowStart++;
            }

            //if the window is valid, we will update the maxLength
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);

        }

        return maxLength;
    }

}
