package leetcode;

/*
Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in O(n) time and without using the division operation.
*/
public class ArrayProductExceptSelf {
    
    public static void main(String[] args) {
        ArrayProductExceptSelf apes = new ArrayProductExceptSelf();
        int[] input = {-1, 1, 0, -3, 3};
        apes.productExceptSelf(input);
    }
    
    //brute force sultion - 2 loops O(N^2) and no division
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        
        for(int i = 0; i < nums.length; i++){
            int product = Integer.MAX_VALUE;
            for (int j = 0; j < nums.length; j++){
                if(j != i){
                    if(product == Integer.MAX_VALUE) product = nums[j];
                    else product = product * nums[j];
                }
            }
            result[i] = product;
        }

        return result;
    }

    //2nd solution: O(n) and no division (kind like dynamic programming)
    //go through the loop twice
    //first loop: get all the product of the numbers befor the number at current index
    //2nd loop: get all the product of the numbers after the number at current index
    public int[] productExceptSelf2(int[] nums) {
        int[] productLeft = new int[nums.length];
        int[] productRight = new int[nums.length];
        int[] result = new int[nums.length];

        productLeft[0] = 1;
        productRight[nums.length - 1] = 1;
        for(int i = 1; i < nums.length; i++){
            int j = nums.length - i - 1;
            productLeft[i] = productLeft[i-1] * nums[i-1];
            productRight[j] = productRight[j+1] * nums[j+1];
        }

        for(int i = 0; i < nums.length; i++){
            result[i] = productLeft[i] * productRight[i];
        }

        return result;
    }

    //same logic as the 2nd solution. but improve the space complexity
    //could you solve it with constant space complexity(The output array does not count as extra space for the purpose of the 
    //space complexity analysis)
    // remove the extra 2 array: productLeft (replaced by the output array), productRight (use a variable)
    public int[] productExceptSelf3(int[] nums) {
        int[] result = new int[nums.length];

        result[0] = 1;
        int productRight = 1;
        for(int i = 1; i < nums.length; i++){
            //after this loop, the output result array will be the product of all elements before.
            result[i] = result[i-1] * nums[i-1];
        }

        for(int j = nums.length - 1; j >= 0; j--){
            result[j] = result[j] * productRight;
            productRight = productRight * nums[j];
        }

        return result;
    }

}
