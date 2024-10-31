package sort;

public class BubbleSort {
    public static void main(String[] args) {
        int [] nums = {10, 1, 25, 3, 2, 11};
        improved_sort(nums);
    }

    static void sort(int[] nums){
        if(nums == null || nums.length <= 1) return ;
        int size = nums.length;

        print(nums);

        boolean swapped = false;
        do{
            swapped = false;
            for(int i = 1; i < size; i++){
                if(nums[i - 1] > nums[i]){
                    //swap them
                    int temp = nums[i];
                    nums[i] = nums[i - 1];
                    nums[i - 1] = temp;
                    //remember something changed
                    swapped = true;
                }
            }
            //after one iteration through the array
            //the largest element has been put into its the last index of the arry
            //n-th pass finds the n-th largest element and puts it into its final place
            //so bubble sort can be improved
            //the inner loop can avoid looking at the last n − 1 items when running for the n-th time:
            size = size - 1;
        } while (swapped);

        print(nums);
    }

    /*
     * More generally, it can happen that more than one element is placed 
     * in their final position on a single pass. 
     * In particular, after every pass, 
     * all elements after the last swap are sorted, 
     * and do not need to be checked again. 
     * This allows us to skip over many elements, 
     * resulting in about a 50% improvement in the worst-case comparison count (though no improvement in swap counts)
     */
    static void improved_sort(int[] nums){
        if(nums == null || nums.length <= 1) return ;
        
        print(nums);

        int compare_size = nums.length;
        do {
            int new_size = 0;
            for(int i = 1; i < compare_size; i++){
                if(nums[i - 1] > nums[i]){
                    int temp = nums[i - 1];
                    nums[i - 1] = nums[i];
                    nums[i] = temp;
                    new_size = i;
                }
            }
            compare_size = new_size;

        } while(compare_size > 1);

        print(nums);
    }

    static void print(int[] nums){
        for(int i = 0; i < nums.length; i++){
            System.out.print(nums[i] + " ");
        }
        System.out.println("");
    }
}
