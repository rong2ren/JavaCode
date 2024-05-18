package Search;

public class MyBinarySearch {
    
    public int binarySearchIteratively(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) return mid;
            else if(nums[mid] < target) left = mid+1;
            else right = mid - 1;
        }
        return - 1;
    }

    public int binarySerachRecursively(int[] nums, int target) {
        return binarySearchHelper(nums, 0, nums.length - 1, target);
    }
    
    private int binarySearchHelper(int[] nums, int left, int right, int target){
        // Restrict the boundary of right index
        // and the left index to prevent
        // overflow of indices
        if(left > right || left > nums.length - 1) return -1;
        
        int mid = left + (right - left) / 2;
        if(nums[mid] == target) return mid;
        else if(nums[mid] > target) return binarySearchHelper(nums, left, mid - 1, target);
        else return binarySearchHelper(nums, mid + 1, right, target);
    }

}
