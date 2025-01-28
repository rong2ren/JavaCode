package Complex;

public class MinimumChanges {

    public static void main(String[] args) {
        System.out.println("change=" + getMinimumChanges2(new int[]{1, 3, 2, 2, 3}, 3));
    }

    /*
     * Amazon's software developers are working on enhancing their inventory management system with a new pricing adjustment operation on an array of products, where the price of the ith product is given by prod_price[i]. The objective is to determine the minimum number of price adjustments needed to ensure that the Amazon price algorithm yields the same price for the sums of all subarrays of length k within the array prod_price.
Price adjustment operations can be performed as follows:
Modify any number of values in the array prod_price[i] to positive integers.
Given the array prod_price and a positive integer k, find the minimum number of changes required so that the sum of elements in all contiguous segments of length k is equal.
Note: A subarray is a contiguous segment of an array.
     */
    public static int getMinimumChanges(int[] prod_price, int k) {
        //first method - kind of naivv, may not be able to handle all cases
        int n = prod_price.length;
        int num_of_subarray = n - k + 1;
        int count = 0;

        //calculate target sum
        int target_sum = 0;
        int[] map = new int[num_of_subarray];//sum of k length subarray starts at index i
        for(int i = 0; i < num_of_subarray; i++){
            int sum = 0;
            for(int j = 0; j < k; j++){
                sum += prod_price[i + j];
            }
            System.out.println("sum from " + i + ":" + (i + k - 1) + "=" + sum);
            target_sum = Math.max(target_sum, sum);
            map[i] = sum;//sum of k length subarray starts at index i
        }

        for(int i = num_of_subarray - 1; i >= 0; i--){
            if(map[i] < target_sum){
                //for each subarray, change the first item
                //the first item is also the last item of the previous subarray
                int change = target_sum - map[i];
                System.out.println("change prod_price[" + i + "]=" + (prod_price[i] + change));
                if(i > 0) map[i - 1] += change;
                count++;
            } else if(map[i] > target_sum){
                System.out.println("something is worng: " + "sum from " + i + ":" + (i + k - 1) + "=" + map[i]);
            }
        }
        
        return count;
    }

    public static int getMinimumChanges2(int[] prod_price, int k) {
        //dynamic programming
        //iterate through the prod_price array
        //consider k elements at a time
        //Let dp[i] be the number of increment operations required to make the subarray consisting of the k size subarray starts at i to meet the requirement
        //dp[0] = 0
        //dp[1] = 1
        int n = prod_price.length;
        int num_of_subarray = n - k + 1;
        int count = 0;
        //int target_sum = -1;
        int previous_sum = -1;

        for(int i = 0; i < n; i++){
            System.out.print(prod_price[i] + " ");
        }
        System.out.println("");

        for(int i = 0; i < num_of_subarray; i++){
            int sum = 0;
            for(int j = 0; j < k; j++){
                sum += prod_price[i + j];
            }
            System.out.println("sum from " + i + ":" + (i + k - 1) + "=" + sum);
            //target_sum = Math.max(target_sum, sum);
            if(i > 0 && sum != previous_sum){
                //sum > preivous_sum -> we need to update the previous substring's first item
                if(sum > previous_sum) prod_price[i-1] += (sum - previous_sum);
                //sum < preivous_sum -> we need to update the current substring's last item
                else prod_price[i + k - 1] += (previous_sum - sum);
                count++;//either way, we just need to update once
            }
            previous_sum = sum;
        }

        for(int i = 0; i < n; i++){
            System.out.print(prod_price[i] + " ");
        }

        return count;

    }
}
