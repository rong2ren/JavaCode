package amazon;

import java.util.Arrays;

public class Throughput {

    public static void main(String[] args) {
        //System.out.println(amazonGetMaxThroughput(new int[]{2, 3, 4, 3, 4}));
        System.out.println(maxTransferRate(new int[]{4,2,5}, 4));
    }

    /*
     * The developers at Amazon are working on optimizing their database query times. There are n host servers, where the throughput of the ith host server is given by host_throughput[i].
These host servers are grouped into clusters of size three. The throughput of a cluster, denoted as cluster_throughput, is defined as the median of the host_throughput values of the three servers in the cluster. Each server can be part of at most one cluster, and some servers may remain unused.
The total system throughput, called system_throughput, is the sum of the throughputs of all the clusters formed. The task is to find the maximum possible system_throughput.
Note: The median of a cluster of three host servers is the throughput of the 2nd server when the three throughputs are sorted in either ascending or descending order.
     */
    public static long amazonGetMaxThroughput(int[] host_throughput) {
        Arrays.sort(host_throughput);
        long max_cluster_throughput = 0;

        int number_of_host = host_throughput.length;
        int number_of_unused = number_of_host % 3;
        for(int i = number_of_unused + 1; i < number_of_host - 1; i = i + 3){
            max_cluster_throughput += host_throughput[i];
        }
        
        return max_cluster_throughput;
    }

    public static long maxTransferRate(int[] throughput, int pipelineCount) {
        //optimally choosing a unique pair of connection for each data pipeline
        //(x,y) and (y,x) are different
        //将 throughput 数组从小到大排序，并计算前缀和数组，用于快速求子数组的和。
        int n = throughput.length;

        // Sort the throughput array
        Arrays.sort(throughput);

        // Compute prefix sum array: to quickly compute the sum of elements in the array for a given range.
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + throughput[i];
        }

        //Perform a binary search to find the largest value of threshold 't' 
        //for which there are at least pipelineCount valid pairs.
        
        //The minimum possible sum of any pair is when both elements in the pair are the smallest.
        //Excludes invalid sums: The smallest possible pair sum is at least throughput[0] * 2, so starting at low ensures we don't waste iterations.
        int low = throughput[0] * 2;
        //The maximum possible sum of any pair is when both elements in the pair are the largest.
        //The value + 1 is added to ensure the binary search includes this maximum value during its iterations. This is a common practice in binary search when we are looking for the boundary or the last valid value.
        int high = throughput[n - 1] * 2 + 1;

        //for all possible transfer rate
        while (low < high) {
            //This mid represents the current candidate value for transfer rate.
            int mid = (low + high) / 2;
            //Use countPairs(mid) to check if there are enough pairs with sum ≥ mid.
            if (countPairs(mid, throughput) >= pipelineCount) {
                low = mid + 1;//mid works, let's check if we can get higher
            } else {
                high = mid;
            }
        }
        //The total number of pairs with sums greater than or equal to t.
        //define the minimum transfer rate t for one pair
        int t = low - 1;

        long totalPairs = 0;
        long totalSum = 0;
        int j = n - 1;
        //Once the threshold t is determined:
        //Iterate through the array to calculate the total sum of pair values
        for (int i = 0; i < n; i++) {
            while (j >= 0 && throughput[i] + throughput[j] >= t) {
                j--;
            }
            int cnt = n - j - 1;
            //For each i, count how many pairs (cnt) include throughput[i] with sum ≥ t.
            totalPairs += cnt;
            totalSum += (long) throughput[i] * cnt + (prefix[n] - prefix[j + 1]);
        }

        // Adjust the total sum for excess pairs
        //If the number of pairs exceeds pipelineCount, 
        //subtract the excess pairs' contribution from the total sum
        long excess = totalPairs - pipelineCount;
        totalSum -= excess * (long) t;

        return totalSum;
    }

    //Function to count the number of pairs whose sum is >= t
    //calculates the number of pairs whose sum is greater than or equal to a given threshold t:
    private static int countPairs(int t, int[] throughput) {
        int n = throughput.length;
        int count = 0;
        int j = n - 1;
        for (int i = 0; i < n; i++) {
            //j moves backward tofind the maximum index where throughput[i] + throughput[j] < t
            while (j >= 0 && throughput[i] + throughput[j] >= t) {
                j--;
            }
            count += n - j - 1;
        }
        return count;
    }
}
