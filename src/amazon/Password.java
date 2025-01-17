package amazon;

import java.util.HashMap;

public class Password {

    public static void main(String[] args) {
        System.out.println(findPasswordStrength("goao"));
    }

    public static int findPasswordStrength(String password) {
        int n = password.length();
        int strength = 0;
        
        // To store the last index of each character
        HashMap<Character, Integer> lastSeen = new HashMap<>();
        int[] distinctCharsCount = new int[n];//Stores the number of distinct characters in all substrings ending at each index.
        /*
         for example: "good" -> the second 'o'
         Substrings ending at "o" are:
            "o" → {o} → Count = 1
            "oo" → {o} → Count = 1 
            "goo" → {g, o} → Count = 2 
            At index 2, the character "o" is repeated.
            Substrings starting before the first "o" (index 1) and ending at index 2 include "goo".
         */
        
        //sliding window - i: window end
        for (int i = 0; i < n; i++) {
            char c = password.charAt(i);
            // If the character is new in this substring - never seen before
            if (!lastSeen.containsKey(c)) {
                //the distinct character count increases to current length
                //for example: good
                //first one is g
                //second includes o and go
                distinctCharsCount[i] = ((i == 0) ? 0 : distinctCharsCount[i - 1]) + (i + 1);
            } else {
                /*
                 * If a character is repeated, 
                 * all substrings that start before the last occurrence of the repeated character and include it are now "affected." 
                 * These substrings should no longer consider the previous occurrence of the character as distinct.
                 */
                
                //step 1: only consider the new substring added, which is c itself
                distinctCharsCount[i] = distinctCharsCount[i - 1] + 1;
                //step 2: sonsider substrings starting after the last occurrence of 'c'
                int lastIndex = lastSeen.get(c);
                //distinctCharsCount[i] consider all substrings ends at i
                //so for substring starts after lastIndex of c, add the new c still make it unique
                for(int j = lastIndex + 1; j < i; j++){
                    distinctCharsCount[i]++;
                }
                //dp[i] = dp[i - 1] + i + 1 - (lastIndex[index] + 1);
                
            }
            lastSeen.put(c, i); // Update last seen index for character
            System.out.println("all substrings ends at " + i + ":" + c + " - count=" + distinctCharsCount[i]);
            
            // Add to total strength
            strength += distinctCharsCount[i];
        }
        
        return strength;
    }
}
