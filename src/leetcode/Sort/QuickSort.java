package Sort;

public class QuickSort {
    public static void main(String[] args) {
        int[] nums = {3,2,1,5,6,4};
        quicksort(nums, 0, nums.length - 1);
        printArray(nums);
    }

    static void swap(int[] nums, int index1, int index2){
        if(index1 == index2) return;
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    static void printArray(int[] arr)
    {
        for(int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
            
        System.out.println();
    }

    static int partition(int[] nums, int left, int right){
        int pivot = nums[right];
        int pivotIdx = left;
        for(int i = left; i < right; i++){
            if(nums[i] < pivot){
                //if current element is smaller than the pivot
                //swap with the right most element
                swap(nums, pivotIdx, i);
                pivotIdx++;
            }
        }

        //put the pivot in the pivotIdx position
        swap(nums, pivotIdx, right);
        System.out.print("pivot index=" + pivotIdx + " ");
        printArray(nums);
        return pivotIdx;
    }

    static void quicksort(int[] nums, int left, int right){
        if(left >= right) return;
        
        // select a pivot and rearrange the array such that
        // elements smaller than pivot are on the left
        // elements greater than pivot are on the right
        int pivotIdx = partition(nums, left, right);

        // recursive call on the left of pivot
        quicksort(nums, left, pivotIdx - 1);
        // recursive call on the right of pivot
        quicksort(nums, pivotIdx + 1, right);
        
    }
}
