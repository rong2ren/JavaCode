package Complex;

public class ReduceMemoryUsage {
/*
 * You are working on an Amazon Data Center where you are required to reduce the amount of main memory consumption by the processes.

Given list of processes where each value representing memory consumption by the processes and given one variable m representing number of processes to be removed. We need to delete m number of processes from the list in contiguous manner and return minimum amount of main memory used by all the processes running after deleting contiguous segment of processes.

 */
    public static void main(String[] args) {
        int[] processes = new int[]{10, 4, 8, 13, 20};
        int m = 2;
        System.out.println(reduceMemoryUsage(processes, m));
    }

    public static int reduceMemoryUsage(int[] processes, int m) {
        //find the subarray with the largest sum: then remove them
        int n = processes.length;
        int total_sum = 0;
        int subarray_max_sum = 0;
        int subarray_curr_sum = 0;
        int start = 0;
        for(int end = 0; end < n; end++){
            total_sum += processes[end];
            subarray_curr_sum += processes[end];
            if(end - start + 1 == m){
                System.out.println("start=" + start + " end="+ end + " sum=" + subarray_curr_sum);
                subarray_max_sum = Math.max(subarray_max_sum, subarray_curr_sum);
                subarray_curr_sum = subarray_curr_sum - processes[start];
                start++;
            }
        }
        
        return total_sum - subarray_max_sum;
    }
}
