package Complex;

import java.util.ArrayList;
import java.util.List;

public class IdealDays {
    public static void main(String[] args) {
        int[] ideal = findIdealDays(new int[]{3, 2, 2, 2, 3, 4}, 2);
        System.out.println(ideal.toString());
    }
    /*
     * According to a survey, a day is ideal for camping if the amount of rainfall has been non-increasing for the prior k days from the considered day, and will be non-decreasing for the following k days from the considered day. Given the predicted rainfall for the next n days, find all the ideal days. Formally, the day is ideal if the following is true:
day[i-k] ≥ day[i-k+1] ≥ ... ≥ day[i-1] ≥ day[i] ≤ day[i+1] ≤ ... ≤ day[i+k-1] ≤ day[i+k]
Return the array of ideal days in ascending order. Note that the ith element of the array represents the data for the day i + 1. It is guaranteed that there is at least one ideal day.
     */
    public static int[] findIdealDays(int[] day, int k) {
        //int day[n]: predicted rainfall for each day
        //k: prior k days and following k days
        //it is a subarray of i - k to i + k (fixed lenght = 2k + 1)

        //sliding window of fixed length = 2k + 1
        List<Integer> result = new ArrayList<>();
        for(int i = k; i < day.length - k; i++){
            //find each turning point that number before is bigger and number after is bigger
            if(day[i-1] >= day[i] && day[i+1] >= day[i]){
                //find a potential i;
                //double check
                boolean isValid = true;
                for(int j = 2; j < k; j++){
                    if(day[i - j] < day[i] || day[i + j] < day[i]) {
                        isValid = false;
                        break;
                    }
                }
                if(isValid) result.add(i + 1);
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();

        //or you can optimize it by doing:
        //sliding window[start, end]
        //using an i iterate through the sliding window, if nums[i]>nums[i-1] -> reset start to i
        
    }
}
