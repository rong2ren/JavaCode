package Complex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class MaxSkill {

    public static void main(String[] args) {
        int[] expertise = new int[]{0, 0, 1, 1, 0, 1, 0};
        int[] skill = new int[]{10, 2, 3, 4};
        getMaxSkillSum(expertise, skill);
    }

    /*
     * A manager at Amazon is managing a team of n employees with IDs numbered from 0 to n - 1. Some employees are marketing experts and others are developers. The employee with id i has a skill level of skill[i]. The employee is a marketing expert if expertise[i] is 0 and a developer if expertise[i] is 1.
     * The manager wants to select a team to work on a project with some contiguous set of ids [i, i + 1, i + 2, ..., j] such that there is an equal number of marketing experts and developers and the total sum of skills is maximized.
Given two arrays, skill and expertise, find the maximum possible sum of skills of any team that can be formed respecting the above conditions.
Note:
It is always possible to form a team consisting of zero employees with a total skill of zero.
A contiguous set of elements is a sequence where each element is adjacent to the next, with no gaps between them. Each element in the set is directly next to the previous one.
     */
    public static long getMaxSkillSum(int[] expertise, int[] skill) {
        //there is an equal number of marketing experts and developers
        int n = expertise.length;
        int[][] dp = new int[n + 1][n + 1];
        //or prefix sum

        /* 
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                dp[i+1][j+1] = dp[i+1][j] + (expertise[j] == 1 ? 1 : -1);
            }
        }
        printArray(dp);*/

        //dp[i][j]: substring [i-1, j-1] - the difference of engineer - marketing
        for(int i = 0; i < n; i++){
            dp[i+1][i+1] = expertise[i] == 1 ? 1 : -1;
            dp[1][i+1] = dp[1][i] + (expertise[i] == 1 ? 1 : -1);
            if(dp[1][i+1] == 0) System.out.println("find one:0-" + i);
            
        }
        //printArray(dp);
        for(int i = 1; i < n; i++){
            for(int j = i + 1; j < n; j++){
                //substring i to j+1 - substring i to i
                dp[i + 1][j + 1] = dp[i][j + 1] - dp[i][i];
                if(dp[i + 1][j + 1] == 0) System.out.println("find one:" + i + "-" + j);
            }
        }
        //may be you can improved to:
        //dp[i][j] = dp[i][j-1] + (expertise[j] == 1 ? 1 : -1);

        

        printArray(dp);

        return 0;
    }

    private static void printArray(int[][] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[0].length; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
