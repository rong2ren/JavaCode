package search;

public class RotatedBinarySearch {

    /*
    binary search inside a rotated sorted array
     * Initialize two pointers left = 0 and right = len(nums) - 1 as the boundaries of our search space.

Initialize pointer mid = (left + right) // 2, and check if nums[mid] == target. If we find the target, we return mid. If it isn't, this removes mid from our search space.

Remember: either left->mid or mid->right is for sure sorted
Determine which half is sorted: check if nums[left] < nums[mid]. If true, the left half is sorted. If not, the right half is sorted.
     */
    public int search(int[] nums, int target)
    {
        int left = 0;
        int right = nums.length - 1;

        //left to k-1: part1
        //k to right: part2

        while (left <= right)
        {
            int mid = (left + right) / 2;

            if ((target < nums[0]) == (nums[mid] < nums[0])){
//CASE1: both target > nums[0] and nums[mid] > nums[0] --> This means both the target and nums[mid] are in part 1, since elements bigger than nums[0] must be in part 1.
//CASE2: both target < nums[0] and nums[mid] < nums[0] --> This means both the target and nums[mid] are in part 2, since elements smaller than nums[0] must be in part 2.
								if (target < nums[mid]) right = mid - 1;
								else if(target > nums[mid]) left = mid + 1;
								else return mid;
						} else if(target < nums[0]) left = mid + 1;//This means target is in part 2, and nums[mid] is in part 1.
							else right = mid - 1;//This means target is in part 1, and nums[mid] is in part 2.
        }
        return -1;
    }
}
