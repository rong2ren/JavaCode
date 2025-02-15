package Sort;

public class QuickSort {
    public static void main(String[] args) {
        int nums[] = {10, 7, 8, 9, 1, 5};
        QuickSort ob = new QuickSort();
        ob.sort(nums, 0, nums.length - 1);
 
        System.out.println("sorted array");
        printArray(nums);
    }

    public void sort(int[] nums, int left, int right){
        if(left > right) return;
        int pivot_index = partition(nums, left, right);

        sort(nums, left, pivot_index - 1);
        sort(nums, pivot_index + 1, right);
    }

    private int partition(int[] nums, int left, int right){
        int pivot_index = left + (right - left) / 2;//mid
        int pivot = nums[pivot_index];
        System.out.println("pivot_index=" + pivot_index + " pivot=" + pivot);

        while(left < right){
            if(nums[left] > pivot){
                //swap left with right
                swap(nums, left, right);
                right--;
            } else left++;
        }
        //put pivot at the right place
        swap(nums, pivot_index, right);
        
        printArray(nums);
        return right;
    }

    private int parition2(int[] nums, int left, int right){
        int pivot = nums[right];
        int pivot_index = left;//next index to put nums smaller than pivot
        for(int i = left; i < right; i++){
            if(nums[i] < pivot){
                swap(nums, i, pivot_index);
                pivot_index++;
            }
        }
        //put the pivot at the right place
        swap(nums, pivot_index, right);
        return pivot_index;
    }

    private int partition3(int[] nums, int start, int end) {
        int pivotInd = start + (int) (Math.random() * (end - start + 1));
        swap(nums, start, pivotInd);//at first, put the pivot at the start index
        int left = start + 1;
        int right = end;
        while(left <= right) {
            while(left <= right && nums[left] <= nums[start]) {
                left++;//increase left until find the first num that is bigger than pivot
            }
            while(left <= right && nums[right] > nums[start]) {
                right--;//decrease right until the first num that is smaller than pivot
            }
            if(left < right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }
        swap(nums, start, right);//put the pivot at the right index
        return right;
    }


    private void swap(int[] nums, int index1, int index2){
        if(index1 == index2) return;
        if(index1 >= nums.length || index2 >= nums.length) return;
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    /* A utility function to print array of size n */
    static void printArray(int nums[])
    {
        for (int i = 0; i < nums.length; i++)
            System.out.print(nums[i]+" ");
        System.out.println();
    }
}
