package leetcode;

public class SubSequence {
    
    public static void main(String[] args) {
        int[] nums = new int[]{11,12,13,14,15,6,7,8,101,18};
        SubSequence ss = new SubSequence();
        System.out.println("output: " + ss.lengthOfLIS(nums));
    }

    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0) return 0;
        
        int[] lastElementArray = new int[nums.length];
        int arrayRightIndex = 0;
        lastElementArray[arrayRightIndex] = nums[0];
        
        for(int i = 1; i < nums.length; i++){
            //for every increasing subsequence with length i + 1, lastElementArray[i] is the smallest tail element.
            //elements in lastElementArray must be increasing order
            
            //if nums[i] is bigger than every element in lastElementArray
            //then append it to lastElementArray
            if(nums[i] > lastElementArray[arrayRightIndex]){
                arrayRightIndex++;
                lastElementArray[arrayRightIndex] = nums[i];
            } else {
                //do binary search
                int right = arrayRightIndex;
                int left = 0;
                while(left <= right){
                    int mid = left + (right - left) / 2;
                    if(nums[i] <= lastElementArray[mid]){
                        if(mid == 0 || nums[i] > lastElementArray[mid - 1]){
                            lastElementArray[mid] = nums[i];
                            break;
                        } else right = mid - 1;
                    }
                    else if(nums[i] > lastElementArray[mid]) {
                        if(nums[i] <= lastElementArray[mid + 1]){
                            lastElementArray[mid + 1] = nums[i];
                            break;
                        } else left = mid + 1;
                    }
                }
            }
        }
        
        return arrayRightIndex + 1;
    }

}
