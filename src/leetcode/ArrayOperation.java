package leetcode;

import java.util.LinkedList;

public class ArrayOperation {
    public static void main(String[] args) {
        int[] input = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21};
        ArrayOperation ap = new ArrayOperation();
        ap.rotate3(input, 29);
        for(int i = 0; i < input.length; i++){
            System.out.print(input[i] + ",");
        }
    }
    
    /***
     * Input: nums = [1,2,3,4,5,6,7], k = 3
     * Output: [5,6,7,1,2,3,4]
     * Explanation:
     * rotate 1 steps to the right: [7,1,2,3,4,5,6]
     * rotate 2 steps to the right: [6,7,1,2,3,4,5]
     * rotate 3 steps to the right: [5,6,7,1,2,3,4]
     * 
     * so numsBeforeK is the [1, 2, 3, 4]
     * [5, 6, 7] will just go to the front of the array
     * 
     * new index for [5, 6, 7] should be the (index + k) % nums.length
     * 
     * time: O(n)
     * space: O(n) -> use extra copy array. find the new index: (index + k) % nums.length or (index + k)
     */
    public static void rotate(int[] nums, int k) {
        if(nums.length == 0 || k <= 0) return;
        //this is very important, when k = nums.length, it rotate k times to the orginal array
        if(k == nums.length) return;
        if(k > nums.length) k = k % nums.length;

        //this array to remember the numbers in the nums array
        int[] numsBeforeK = new int[nums.length - k];

        for(int i = 0; i < nums.length; i++){
            if(i >= nums.length - k) nums[i - (nums.length - k)] = nums[i];
            else numsBeforeK[i] = nums[i];
        }

        for(int j = 0; j < numsBeforeK.length; j++){
            nums[k + j] = numsBeforeK[j];
        }
    }

    

    /**
     * this solution uses a linkedlist, not good, use a lot of memory
     */
    public void rotate2(int[] nums, int k) {
        if(nums.length == 0 || k <= 0) return;
        //this is very important, when k = nums.length, it rotate k times to the orginal array
        if(k == nums.length) return;
        if(k > nums.length) k = k % nums.length;

        LinkedList<Integer> numList = new LinkedList<Integer>();

        for(int i = 0; i < nums.length; i++){
            if(i >= nums.length - k) numList.add(i - (nums.length - k), nums[i]);
            else numList.add(nums[i]);
        }

        for(int j = 0; j < numList.size(); j++){
            nums[j] = numList.get(j);
        }

    }


    /**
     * modify the array in place
     * 1. reverse the whole array
     * [1, 2, 3, 4, 5, 6, 7] -> [7, 6, 5, 4, 3 ,2 ,1]
     * 2. reverse the first k elements
     * [7, 6, 5, 4, 3 ,2 ,1] -> [5, 6, 7, 4, 3, 2 ,1]
     * 3. reverse the rest elements
     * [5, 6, 7, 4, 3, 2 ,1] -> [5, 6, 7, 1, 2, 3, 4]
     */
    public void rotate3(int[] nums, int k) {
        if(nums.length == 0 || k <= 0 || k == nums.length) return ;
        if(k > nums.length) k = k % nums.length;

        reverseArray(nums, 0, nums.length - 1);
        reverseArray(nums, 0, k - 1);
        reverseArray(nums, k, nums.length - 1);
    }

    private void reverseArray(int[] nums, int left, int right){
        if(nums.length == 0 || left < 0 || right < 0 || right > nums.length) return ;
        while(left < right){
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }



    /**
     * Failed Solution
     * Could you do it in-place with O(1) extra space?
     * [1, 2, 3, 4, 5, 6, 7, 8] (k = 3) (array.length = 8)
     * swap until index is at k - 1: [6, 7, 8, 1]
     * then while index is from k to array.length - 1, continue swap: [5, 6, 7, 1, 4, 2, 3]
     *  this method doesn't work for all conditions, for example:
     * [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21] k=29
     */
    public void rotateSolutionFailed(int[] nums, int k) {
        if(nums.length == 0 || k == 0) return;
        //this is very important, when k = nums.length, it rotate k times to the orginal array
        if(k > nums.length) k = k % nums.length;
        if(k == nums.length || k <= 0) return;

        int index = 0;
        while(index < nums.length){
            index = rotateWithParameter(nums, index, k);
        }
    }

    private int rotateWithParameter(int[] nums, int start, int k){
        if(nums.length == 0 || k == 0) return nums.length;
        //this is very important, when k = nums.length, it rotate k times to the orginal array
        //if(k > nums.length) k = k % nums.length;
        if(k == nums.length || k <= 0) return nums.length;

        int swapIndex = nums.length - k;
        if(swapIndex == start) return nums.length;
        else if(swapIndex > start){
            for(int i = swapIndex; i < nums.length; i++){
                swap(nums, start, i);
                start++;
            }
        }
        else if(swapIndex < start){
            if(k == start) return nums.length;
            else {
                while(start < nums.length){
                    swap(nums, start, nums.length - 1);
                    start++;
                }
            } 
        } 
        
        return start;

    }

    private void swap(int[] nums, int s1, int s2){
        if(nums.length == 0 || s1 < 0) return;
        if(s2 >= nums.length || s1 >= nums.length) return;
        
        int before = nums[s1];
        nums[s1] = nums[s2];
        nums[s2] = before;
    }



    /**
     * Given an integer array nums and an integer val, 
     * remove all occurrences of val in nums in-place. 
     * The relative order of the elements may be changed.
     * Since it is impossible to change the length of the array in some languages, 
     * you must instead have the result be placed in the first part of the array nums. 
     * More formally, if there are k elements after removing the duplicates, 
     * then the first k elements of nums should hold the final result. 
     * It does not matter what you leave beyond the first k elements.
     * 
     * Return k after placing the final result in the first k slots of nums.
     * Do not allocate extra space for another array. 
     * You must do this by modifying the input array in-place with O(1) extra memory.
     * 
     * Input: nums = [3,2,2,3], val = 3
     * Output: 2, nums = [2,2,_,_]
     * Explanation: Your function should return k = 2, with the first two elements of nums being 2.
     * It does not matter what you leave beyond the returned k (hence they are underscores).
     */
    public int removeElement(int[] nums, int val) {
        if(nums.length <= 0) return 0;

        //we will use two pointers, left and right
        //相向双指针方法，基于元素顺序可以改变的题目描述改变了元素相对位置，确保了移动最少元素
        int right = nums.length - 1;
        int left = 0;

        while(left <= right){   
            if(nums[left] == val){
                while(right >= left && nums[right] == val){
                    right--; //find the right index with value != val
                }

                if(left >= right) {
                    break;
                } else {                    
                    nums[left] = nums[right];//replace left element with the right element
                    right--;   
                } 
            }
            left++;
        }

        return left;
    }

    public int removeElementWithSlowAndFastPointer(int[] nums, int val) {
        if(nums.length <= 0) return 0;

        int slowIndex = 0;
        for(int fastIndex = 0; fastIndex < nums.length; fastIndex++){
            if(nums[fastIndex] != val){
                if(fastIndex != slowIndex){
                    //replace slowIndex with fastIndex elements
                    //注意这实现方法并没有改变元素的相对位置！
                    nums[slowIndex] = nums[fastIndex];
                }
                slowIndex++;
            }
        }
        return slowIndex;
    }


    /**
     * Given an array of positive integers nums and a positive integer target, 
     * return the minimal length of a contiguous subarray [numsl, numsl+1, ..., numsr-1, numsr] 
     * of which the sum is greater than or equal to target. 
     * If there is no such subarray, return 0 instead.
     * Input: target = 7, nums = [2,3,1,2,4,3]
     * Output: 2
     * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
     * 
     * solution: sliding window
     */
    public int minSubArrayLen(int target, int[] nums) {
        if(nums.length <= 0) return 0;

        int windowStart = 0;
        int currentSum = 0;
        int currentLength = 0;
        int minSubArrLen = Integer.MAX_VALUE;
        for(int windowEnd = 0; windowEnd < nums.length; windowEnd++){
            currentSum = currentSum + nums[windowEnd];
            //below use while instead of if
            // 注意这里使用while，每次更新 windowStart，并不断比较子序列是否符合条件

            while(currentSum >= target){
                //find a window
                currentLength = windowEnd - windowStart + 1;
                minSubArrLen = Math.min(minSubArrLen, currentLength);

                //move the windowStart
                currentSum = currentSum - nums[windowStart];
                windowStart++;
            } 
        }

        if(minSubArrLen == Integer.MAX_VALUE) minSubArrLen = 0;
        return minSubArrLen;
    }

}
