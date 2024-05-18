package test;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        //int[] nums = {-10000,-9999,-7,-5,0,0,10000};
        int[] nums = {-3,0,2};
        s.sortedSquares(nums);
        
    }

    public int[] sortedSquares(int[] nums) {
        int size = nums.length;
        int[] output = new int[size];

        int last_negative_i = -1;//index for the last negative number in the input array
        int output_i = 0;//index for output arrary

        if(nums[size - 1] <= 0){
            for(int i = size - 1; i >= 0; i--){
                output[output_i] = nums[i] * nums[i];
                output_i++;
            }
        } else {
            int i = 0;
            while(i < nums.length){
                if(nums[i] < 0) {
                    last_negative_i++;
                    i++;
                }
                else {
                    int next_square;
                    if(last_negative_i >= 0){
                        if(nums[i] < nums[last_negative_i] * -1){
                            next_square = nums[i] * nums[i];
                            i++;
                        } else {
                            next_square = nums[last_negative_i] * nums[last_negative_i];
                            last_negative_i--;
                        }
                    } else {
                        next_square = nums[i] * nums[i];
                        i++;
                    }
                    output[output_i++] = next_square;
                }
            }
        }
        
        

        return output;
    }
}
