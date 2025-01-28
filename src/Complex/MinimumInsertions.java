package Complex;


public class MinimumInsertions {

    /*
     * Amazon CodeCraft introduces you to an engaging problem-solving challenge. You are presented with two integer lists named as: initialList and finalList of length m and n respectively.

The finalList contains unique integers, while the initialList may contain duplicates. You are allowed to perform the following operation any number of times (possibly zero):

Insert any positive integer at any position (including the start and the end of the list) in the initialList.
Your task is to determine the minimum number of operations required to transform the initialList into a subsequence of the finalList. It can be proven that it is always possible to transform the initialList into a subsequence of the finalList.

Note: A subsequence of an array is a sequence that can be derived from the array by deleting some or no elements, without changing the order of the remaining elements.
     */
    public static void main(String[] args) {
        int[] finalList = new int[]{5, 1, 3};
        int[] initialList = new int[]{9, 4, 2, 3, 4};
        int count = minimumInsertions(finalList,initialList);
        System.out.println("count=" + count);
    }

    public static int minimumInsertions(int[] finalList, int[] initialList) {
        /*
         * This problem can be solved using a dynamic programming (DP) approach, 
         * as it involves finding the Longest Common Subsequence (LCS) between initialList and finalList.
         * The minimum number of insertions required will be:
         * Insertions=Length of finalist−Length of LCS(initialList, finalList)
         */
        int m = initialList.length;
        int n = finalList.length;
        int[][] dp = new int[m+1][n+1];//common subsequence
        //initalize base case: empty initalList with empty finalList
        dp[0][0] = 0;

        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(initialList[i-1] == finalList[j-1]) dp[i][j] = dp[i-1][j-1] + 1;
                else dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
            }
        }
        return n - dp[m][n];
    }
}
