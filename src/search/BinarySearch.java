package search;

public class BinarySearch {

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,6,9};
        int position = searchInsert(nums, 4);
        System.out.println("insert postiion:" + position);
    }

    public static int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int middle = left + (right - left) / 2;
            if(target < nums[middle]) right = middle - 1;
            else if(target > nums[middle]) left = middle + 1;
            else return middle;
        }
        return left;
    }


}
