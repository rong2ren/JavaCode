package Complex;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class BuyVolumn {

    /*
     * Amazon Books is a retail store that sells the newly launched novel "The Story of Amazon". The novel is divided into volumes numbered from 1 to n and unfortunately, all the volumes are currently out of stock.

The Amazon team announced that starting today, they will bring exactly one volume of "The Story of Amazon" in stock each of the next n days. On the nth day, all volumes will be there. Being an impatient bookworm, each day you will purchase the maximum number of volumes you can such that:

You have not purchased the volume before.
You already own all its direct prior volumes.
Note: For the ith volume of the novel, all the volumes such that j < i are its prequels.

Determine the volumes you would purchase each day. You should return an array of n arrays where the ith array contains:

the volume numbers in ascending order if you purchased some volumes on the ith day
the single element -1 if you did not purchase any book
     */
    public static void main(String[] args) {
        int[] volumes = new int[]{2, 1, 4, 3};
        int[][] bought = buyVolumes(volumes);
        for(int i = 0; i < bought.length; i++){
            System.out.print("day" + i + ": ");
            for(int j = 0; j < bought[i].length; j++){
                System.out.print(bought[i][j] + ",");
            }
            System.out.println("");
        }
    }
    
    public static int[][] buyVolumes(int[] volumes) {
        int n = volumes.length;
        int last_purchased = 0;
        List<List<Integer>> purchased = new ArrayList<>(n);
        PriorityQueue<Integer> available = new PriorityQueue<>();//min_heap

        for(int i = 0; i < n; i++){
            List<Integer> buy_today = new ArrayList<>();
            if(volumes[i] - last_purchased == 1){
                //you can buy volumes[i]
                buy_today.add(volumes[i]);
                last_purchased = volumes[i];
                //check available
                while(!available.isEmpty() && available.peek() - last_purchased == 1){
                    int buy = available.poll();
                    buy_today.add(buy);
                    last_purchased = buy;
                }
            } else {
                //add to the available
                buy_today.add(-1);
                available.add(volumes[i]);
            }
            purchased.add(buy_today);
        }

        return convertTo2DArray(purchased);
    }

    public static int[][] convertTo2DArray(List<List<Integer>> list) {
        // Create a 2D array with the size of the outer list
        int[][] result = new int[list.size()][];

        // Populate the 2D array
        for (int i = 0; i < list.size(); i++) {
            List<Integer> innerList = list.get(i);
            result[i] = new int[innerList.size()]; // Allocate space for each row
            for (int j = 0; j < innerList.size(); j++) {
                result[i][j] = innerList.get(j); // Copy elements
            }
        }
        return result;
    }
}
