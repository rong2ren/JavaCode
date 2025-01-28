package Complex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PanBalance {
    private static final int MOD = 1000000007;

    public static void main(String[] args) {
        int[] weights = new int[]{4,5,5,4,4,5,2,3};
        int wt = 1;
        System.out.println(countPairs(weights, wt));
    }

    public static long countPairs(int[] weights, int wt){
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        long pairs_count = 1;
        // Calculate frequencies
        for (int weight : weights) {
            frequencyMap.put(weight, frequencyMap.getOrDefault(weight, 0) + 1);
        }
        if(wt == 0){
            // Handle the case where wt == 0, you need to find pairs with same weight
            for (int count : frequencyMap.values()) {
                if (count % 2 != 0) {
                    //If we have an odd number of occurrences of a number, it's impossible to pair them up completely 
                    //(e.g., 3 occurrences of 5 means there will be one leftover 5, which can't form a pair).
                    return 0;
                }
                //calculate the number of ways to form pairs with same number
                //for each number, we divide the count by 2 to form pairs. 
                //For example, if a number appears 6 times, we can form 3 pairs, 
                //and we compute how many ways we can arrange these pairs.
                int p = count / 2;//this determines how many pairs can be formed
                long num = factorial(count);//this calculates the total number of ways to arrange the count numbers.
                //modPow(2, p):
                //the pair {a, b} is the same as {b, a}, 
                //so for each of the p pairs, there are 2^p arrangements that need to be discounted.
                //factorial(p):
                //The p pairs themselves can be arranged among each other, but since they are indistinguishable, the arrangements of the pairs do not count as unique.
                long den = (long)Math.pow(2, p) * factorial(p);
                //This calculates the final number of valid pairings by dividing the total arrangements (num) by the overcounting factor (den).
                //num / den : the number of unique ways to divide (count) identical numbers into (p) pairs.
                pairs_count = (pairs_count * (num / den)) % MOD;
            }
        } else {
            // Handle the case where wt != 0
            // find all pairs (weight1, weight2) such that weight2 - weight1 = wt
            // ensure that no element is used more than its frequency
            List<Integer> keys = new ArrayList<>(frequencyMap.keySet());
            Collections.sort(keys);//Sorting ensures that we process numbers in increasing order.
            for (int key : keys) {
                int target = key + wt; //since it is sorted, so we only need to check target which is (wt) bigger
                if (frequencyMap.containsKey(target)) {
                    //the number of valid pairs is limited by the smaller frequency of the two elements
                    int key_count = frequencyMap.get(key);
                    int target_count = frequencyMap.get(target);
                    int k = Math.min(key_count, target_count);
                    if (k == 0) {
                        continue;
                    }
                    //to form k paris, we use combinations
                    //number of ways to choose k elements from all occurency of keys
                    //number of ways to chooose k elements from all occurency of targets
                    //math.factorial(k): Accounts for all permutations of the k pairs
                    long c1 = combination(key_count, k);
                    long c2 = combination(target_count, k);
                    long ways = (c1 * c2) * factorial(k) % MOD;
                    pairs_count = pairs_count * ways % MOD;
                    frequencyMap.put(key, key_count - k);
                    frequencyMap.put(target, target_count - k);
                } else {
                    if (frequencyMap.get(key) > 0) {
                        //cannot find target, but key's count is not 0
                        return 0;
                    }
                }
            }
            // Check if all counts are zero
            for (int count : frequencyMap.values()) {
                if (count != 0) {
                    return 0;
                }
            }
        }

        return pairs_count;
    }


    private static long factorial(int n) {
        if (n == 0 || n == 1) return 1;
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result = (result * i) % MOD;
        }
        return result;
    }

    private static long combination(int n, int r) {
        if (r < 0 || r > n) {
            return 0; // Or throw an exception for invalid input
        }
        return factorial(n) / (factorial(r) * factorial(n - r));
    }
}
