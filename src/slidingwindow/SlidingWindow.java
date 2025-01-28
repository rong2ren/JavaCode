package slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SlidingWindow {

    /*
     * Given a string s, find the length of the longest substring without repeating characters.
     */

     /*
      * Approach 1:
      We use a set (seen) to keep track of unique characters in the current substring.
        We use two pointers: left and right to represent the window.
        The right pointer moves to the right to expand the window.
        If the current character is not in the set (seen), it means we have a new unique character.
        We insert the character into the set and update the length if necessary.
        If the character is already present in the set, it indicates a repeating character within the current substring.
        In this case, we move the left pointer forward, removing characters from the set until the repeating character is no longer present.
        We insert the current character into the set and continue the iteration.
        Finally, we return the length as the length of the longest substring without repeating characters.
      */
     public int lengthOfLongestSubstring1(String s) {
        if(s == null || s.length() <= 0) return 0;
        if(s.length() == 1) return 1;

        int length = 0;
        Set<Character> seen = new HashSet<>();

        int left = 0;
        seen.add(s.charAt(left));

        int right = left + 1;
        //iterate characters by right pointer
        while(right < s.length()){
            char c = s.charAt(right);
            if(seen.contains(c)){
                //shrink window by left pointer
                //removing characters from the set until the repeating character is no longer present.
                while(seen.contains(c)){
                    seen.remove(s.charAt(left));
                    left++;
                }
            }
            //increase window by right pointer
            seen.add(c);
            right++;
            //update the length
            length = Math.max(length, seen.size());
        }

        return length;
    }

    /*
     * Approach 2:
     * We improve upon the first solution by using an unordered map (charMap) instead of a set.
     * The map stores the last seen index of each character.
     * We use two pointers: left and right to represent the window.
     * The right pointer moves to the right to expand the window.
     * If the current character is not in the map (charMap), it means we have a new unique character.
     * We insert the character into the map and update the length if necessary.
     * If the character is already present in the map, it indicates a repeating character within the current substring.
     * In this case, we move the left pointer forward to the last seen index of the repeating character.
     * We update the length and update the last seen index of the repeating character in the map.
     * Finally, we return the length as the length of the longest substring without repeating characters.
     */
    public int lengthOfLongestSubstring2(String s) {
        if(s == null) return 0;

        int size = s.length();
        if(size <= 1) return size;

        int length = 0;
        int left = 0;
        Map<Character, Integer> last_seen = new HashMap<>();//last seen index of the character

        for(int right = 0; right < size; right++){
            char c = s.charAt(right);
            if(last_seen.containsKey(c) && last_seen.get(c) >= left){
                //repeat character
                //shrink the window by set the left
                left = last_seen.get(c) + 1;
            }
            last_seen.put(c, right);
            length = Math.max(length, right - left + 1);
        }
        return length;
    }
}
