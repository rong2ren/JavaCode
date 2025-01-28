package Complex;

import java.util.Arrays;

public class MininumPenDriver {

    public static void main(String[] args) {
        int[] gameSize = new int[]{9,2,4,6};
        int k = 3;
        System.out.println(getMinSize(gameSize, k));
    }

    /*
     * gameSize: the size of each game
     * k: the number of children amongst whom the games are to be distributed. every child must receive at least one game
     * 
     * return the minimum capacity of pen drivers required to distribute the games among children
     */
    private static long getMinSize(int[] gameSize, int k){
        int n = gameSize.length;
        Arrays.sort(gameSize);
        long left = gameSize[n-1]; // Maximum single game size
        long right = 0;// Total size of all games
        for(int i = 0; i < n; i++){
            right += gameSize[i];
        }
        while(left < right){
            long mid = left + (right - left)/2;
            // Check if the current mid capacity is feasible
            if (canDistribute(gameSize, k, mid)) {
                right = mid; // Search in the left half
            } else {
                left = mid + 1; // Search in the right half
            }
        }
        return left;
    }

    private static boolean canDistribute(int[] gameSize, int number_of_car, long car_capacity){
        int count = 0;
        int left = 0, right = gameSize.length - 1;
        while (left <= right) {
            // If the largest game is greater than capacity, it's not possible
            if (gameSize[right] > car_capacity) {
                return false;
            }
            // If the smallest and largest games together fit within capacity, pair them
            if (gameSize[left] + gameSize[right] <= car_capacity) {
                left++;
                right--;
            } else right--;//only put the (right) game in
		    count++;

            if (count > number_of_car) {
                return false;
            }
        }
        return true;
    }
}
