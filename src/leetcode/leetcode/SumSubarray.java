package leetcode;

import java.util.HashMap;
import java.util.Map;

public class SumSubarray {
    public static void main(String[] args) {
        int[] input = new int[]{4, 2, 1, 7, 8, 1, 2, 8 , 1, 0};
        System.out.println(findMaxSumSubarray(input, 3));
    }

    /**
     * Find the max sum subarray of a fixed size k
     * fixed window size
     */
    public static int findMaxSumSubarray(int[] arr, int k){
        if (arr.length <= 0) return 0;

        int maxValue = Integer.MIN_VALUE;
        int currentRunningSum = 0;

        for(int i = 0; i < arr.length; i++){
            currentRunningSum = currentRunningSum + arr[i]; //add value to the current running sum
            if(i >= k - 1){
                //value i tells how far away we in the index: it is the k th elements.
                maxValue = Math.max(currentRunningSum, maxValue);
                currentRunningSum = currentRunningSum - arr[i - (k - 1)];//sliding the window, minus the first elements and add the next elements later
            }
        }

        return maxValue;
    }


    /**
     * Find the smallest subarray with given sum
     * e.g. smallest subarray with sum >= 8
     * use dynamically resizing window: linear time 
     */
    public static int findMinSumSubarray(int[] arr, int targetSum){
        if (arr.length <= 0) return 0;
        int minWindowSize = Integer.MAX_VALUE;
        int windowStart = 0;
        int currentRunningSum = 0;

        for(int windowEnd = 0; windowEnd < arr.length; windowEnd++){
            currentRunningSum += arr[windowEnd];
            while(currentRunningSum >= targetSum){
                minWindowSize = Math.min(minWindowSize, windowEnd - windowStart + 1);
                currentRunningSum = currentRunningSum - arr[windowStart];
                windowStart++;//shrink the window left size
            }
        }

        return minWindowSize;
    }


    /**
     * longest substring length with k distinct characters
     * example: AAAHHIBC, find longest substring with only 2 distinct character
     * sliding window with Hashmap
     */
    public static int findLongestSubstring(String s, int k){
        if(s == null || s.length() <= 0) return 0;
        if(s.length() <= 1) return 1;

        int maxLength = Integer.MIN_VALUE;
        Map<Character, Integer> charFrequencyMap = new HashMap<Character, Integer>(); //keep check of the character count
        
        int windowStart = 0;

        for(int windowEnd = 0; windowEnd < s.length(); windowEnd++){
            char rightChar = s.charAt(windowEnd);
            charFrequencyMap.put(rightChar, charFrequencyMap.getOrDefault(rightChar, 0) + 1);

            while(charFrequencyMap.size()>k){
                char leftChar = s.charAt(windowStart);
                charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) - 1);
                if(charFrequencyMap.get(leftChar) == 0) charFrequencyMap.remove(leftChar);
                windowStart++;
            }
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);

        }
        return maxLength;
    }
    
}
