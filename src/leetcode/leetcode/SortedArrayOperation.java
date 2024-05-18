package leetcode;


public class SortedArrayOperation {
    public static void main(String[] args) {
        int[] input = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21};
        //SortedArrayOperation sap = new SortedArrayOperation();
        
        for(int i = 0; i < input.length; i++){
            System.out.print(input[i] + ",");
        }
    }
    
    /**
     * Binary serach in Array
     * Given an array of integers nums which is sorted in ascending order, 
     * and an integer target, write a function to search target in nums. 
     * If target exists, then return its index. Otherwise, return -1.
     * 
     * You must write an algorithm with O(log n) runtime complexity.
     * All the integers in nums are unique. 
     * nums is sorted in ascending order.
     */
    public int search(int[] nums, int target) {
        if(nums.length <= 0) return -1;
        if(target < nums[0] || target > nums[nums.length - 1]) return -1;
        
        int left = 0; 
        int right = nums.length - 1;
        while(left <= right){ // 定义target在左闭右闭的区间里，[left, right]
            int length = right - left + 1;
            int middle = left + (length / 2);

            if(target > nums[middle]){ // target 在右区间，所以[middle + 1, right]
                left = middle + 1;
            } else if(target < nums[middle]){ // target 在左区间，所以[left, middle - 1]
                right = middle - 1;
            } else return middle; // nums[middle] == target
        }
        return -1; // 未找到目标值
    }


    /**
     * Given a sorted array of distinct integers and a target value, 
     * return the index if the target is found. 
     * If not, return the index where it would be if it were inserted in order.
     * 
     * You must write an algorithm with O(log n) runtime complexity.
     * nums contains distinct values sorted in ascending order.
     */
    public int searchInsert(int[] nums, int target) {
        if(nums.length == 0) return 0; //empty array, insert at 0;
        if(target < nums[0]) return 0; //目标值在数组所有元素之前
        if(target > nums[nums.length - 1]) return nums.length; //目标值在数组所有元素之后


        //below codes handles
        //1.目标值等于数组中某一个元素
        //2. 目标值插入数组中的位置
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int length = right - left + 1;
            int middle = left + length / 2;
            if(target > nums[middle]){
                left = middle + 1;
            } else if(target < nums[middle]){
                right = middle - 1;
            } else return middle;
        }

        return left;
    }


    /**
     * Given an array of integers nums sorted in non-decreasing order, 
     * find the starting and ending position of a given target value.
     * If target is not found in the array, return [-1, -1].
     * You must write an algorithm with O(log n) runtime complexity.
     * Input: nums = [5,7,7,8,8,10], target = 8
     * Output: [3,4]
     * 
     * Use binary search to find the element index first. then find the window
     */
    public int[] searchRange(int[] nums, int target) {
        if(nums.length == 0 || target < nums[0] || target > nums[nums.length - 1]) 
            return new int[]{-1, -1};

        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int length = right - left + 1;
            int middle = left + length / 2;

            if(target > nums[middle]){
                left = middle + 1;
            } else if(target < nums[middle]){
                right = middle - 1;
            } else {
                //find it
                int outputLeftIndex = middle;
                while(outputLeftIndex >= 1){
                    if(nums[outputLeftIndex - 1] == target) outputLeftIndex--;
                    else break;
                }
                int outputRightIndex = middle;
                while(outputRightIndex <= nums.length - 2){
                    if(nums[outputRightIndex + 1] == target) outputRightIndex++;
                    else break;
                }
                return new int[]{outputLeftIndex, outputRightIndex};
            }
        }

        return new int[]{-1, -1};
    }



    /**
     * Given an integer array nums sorted in non-decreasing order, 
     * return an array of the squares of each number sorted in non-decreasing order.
     * Input: nums = [-4,-1,0,3,10]
     * Output: [0,1,9,16,100]
     * Explanation: After squaring, the array becomes [16,1,0,9,100].
     * After sorting, it becomes [0,1,9,16,100].
     * 
     * 数组其实是有序的， 只不过负数平方之后可能成为最大数了。
     * 那么数组平方的最大值就在数组的两端，不是最左边就是最右边，不可能是中间。
     * Two pointers
     */
    public int[] sortedSquares(int[] nums) {
        if(nums.length <= 0) return nums;

        int[] output = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;
        int index = nums.length - 1;
        while(left <= right){
            if(nums[left] * nums[left] > nums[right] * nums[right]){
                output[index] = nums[left] * nums[left];
                left++;
                index--;
            } else {
                output[index] = nums[right] * nums[right];
                right--;
                index--;
            }
        }
        return output;
    }
}
