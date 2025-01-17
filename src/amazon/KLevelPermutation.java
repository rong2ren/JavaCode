package amazon;

import java.util.ArrayList;
import java.util.List;

public class KLevelPermutation {
/*
https://www.fastprep.io/problems/amazon-find-k-level-permutation

 * What you're solving for 
The K-level permutation of the array from 1 to N. 
What's given in the problem 

• You have an array from 1 to N 
• You have to find a K-level permutation 
• For N-K+1 segments or windows you get the difference between the sum of the maximum segment sum and the minimum segment sum must be at most 1 

Helpful information 

• A permutation is an arrangement of all or part of a set of objects, with regard to the order of the arrangement 
• A segment or window is a contiguous subsequence of an array 
• The sum of a segment is the sum of all the elements in that segment 

How to solve 
Find a pattern for constructing the permutation that satisfies the given conditions. 

1. Step 1 Understand the problem. 
	• The problem is asking us to find a way to arrange the numbers 1 to N in a specific order such that the difference between the sum of the maximum segment sum and the minimum segment sum is at most 1. 


2. Step 2 Find a pattern for K = 1. 
	• For K = 1, the permutation is simply the original array 1, 2, 3, ..., N. 


3. Step 3 Find a pattern for K = 2. 
	• For K = 2, we can alternate between the smallest and largest remaining elements. 
	• 1, N, 2, N-1, 3, N-2, ... 


4. Step 4 Find a pattern for K > 2. 
	• For K > 2, we can generalize the pattern. Divide the array into K groups. Fill the first group with the smallest elements, the second group with the next smallest elements, and so on. Interleave the elements from the groups. 



Solution 
The K-level permutation of the array from 1 to N can be found by dividing the array into K groups and interleaving the elements from the groups. 
 */
    public static void main(String[] args) {
        int N = 9, K = 4;
        List<List<Integer>> result = generateKLevelPermutations(N, N);

        System.out.println("all possible permutations: N=" + N);
        int count = 0;
        for (List<Integer> permutation : result) {
            //check if this permutation meet the requirement: sum N-K+1 segement (length K) is difffer by 1
            if(isKLevelPermutation(permutation, K)) {
                //System.out.println(permutation);
                count++;
            }
        }
        System.out.println("toatl count=" + count);

        System.out.println("the only permutation with sum difference at most 1: N=" + N + " K=" + K);
        int[] permuation = findKLevelPermutation(N, K);
        for(int i = 0; i < permuation.length; i++){
            System.out.print(permuation[i] + ",");
        }
        System.out.println("there are total: " + (N - K + 1) + " segments");
        for(int i = 0; i < N - K + 1; i++){
            int sum = 0;
            for(int j = i; j < i + K; j++){
                System.out.print(permuation[j] + "+");
                sum += permuation[j];
            }
            System.out.println("=" + sum);
        }
    }

    public static int[] findKLevelPermutation(int N, int K) {
        int left = 1, right = N;
        //segment_length = K;
        //number_of_segment = N - K + 1;
        //always start the pair: for example [1,2],[3,4], [5], [6,7]
        //K = 4
        //1, 7, _, _, 2, 6, _, _
        //first segment: 1,7,_,_
        //second segment: 7,_,_,2
        //third segment: _,_, 2,6
        //as you can see: swift from first to second, the segment head is removed and new segment end is added
        //in order for the sum to differ at most 1,
        //so you can remove 1 and add 2

        int[] permutation = new int[N];
        for(int i = 0; i < N; ){
            permutation[i] = left;
            left++;
            if(i + 1 < N){
                permutation[i+1] = right;
                right--;
            }
            i = i + K;
        }
        
        for(int i = 0; i < N; i++){
            if(permutation[i] == 0){
                //no number at this index yet
                if(i % 2 == 0){
                    //increase position
                    permutation[i] = left;
                    if(i + K < N){
                        permutation[i + K] = left + 1;
                        left = left + 2;
                    } else left++;
                } else {
                    //decreasing
                    permutation[i] = right;
                    if(i + K < N){
                        permutation[i + K] = right - 1;
                        right = right - 2;
                    } else right++;
                }
            }
        }

        return permutation;
    }

    /*
     * N = 7, array = {1,2,3,4,5,6,7}
     * to construct segments of length K = 3 with total sum differ by 1
     */
    public static int[] findKLevelPermutation_old(int N, int K) {
        /*
         * You have an array from 1 to N now you have to find a K-level permutation such that for N-K+1 segments or windows you get the difference between sum of maximum segment sum and minimum segment must be at most 1.
         */
        int[] permutation = new int[N];

        int num_of_segments = N - K + 1;//N−K+1 represents the number of contiguous subarrays (segments) of size K
        
        int left = 1;
        //define a segment: length of K: window_start-> window_start + k - 1
        //construct first segment of length k
        for(int i = 0; i < K; i=i+2){
            permutation[i] = left;
            permutation[i + 1] = N + 1 - left;
            //you have to increase left by 2 because the next number will be put as the next window's end number
            left = left + 2;
            int skip = i + K;//can I put next left into current i?
            while(skip < num_of_segments){
                left++;
                skip += K;
            }

        }
        int rotate = 1;
        for(int i = K; i < N; i++){
            //+1, -1 rotate: to make the total sum differ by 1
            //last window = [i - k, i - 1]
            //current window [i - k + 1, i]
            //so current window end should differ by last window start by 1 (+1, or -1)
            permutation[i] = permutation[i - K] + 1 * rotate;
            rotate = 0 - rotate;
        }

        return permutation;
    }

    private static List<List<Integer>> generateKLevelPermutations(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        generatePermutations(n, k, new ArrayList<>(), result);
        return result;
    }

    private static void generatePermutations(int n, int k, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!current.contains(i)) {
                current.add(i);
                generatePermutations(n, k, current, result);
                current.remove(current.size() - 1);
            }
        }
    }

    private static boolean isKLevelPermutation(List<Integer> permutation, int k){
        int window_start = 0;
        int window_end = -1;
        int sum = 0;
        int max_sum = -1;
        int min_sum = -1;
        for(int num : permutation){
            window_end++;
            sum += num;
            if(window_end - window_start + 1 == k){
                if(max_sum == -1) max_sum = sum;
                else if(sum>max_sum) max_sum = sum;

                if(min_sum == -1) min_sum = sum;
                else if(sum<min_sum) min_sum = sum;

                if(max_sum - min_sum > 1) return false;
                sum = sum - permutation.get(window_start);
                window_start++;
                
            }
        }
        return true;
    }
 
}
