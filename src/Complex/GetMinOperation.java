package Complex;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GetMinOperation {
    /*
     * Devs at AMZ are working on a new sorting algorithm for points on the x-axis
     * of the coordinate system.
     * 
     * There are n points. The ith point initially has a weight of weight[i] and is
     * located at position i on the x-axis. In a single position, the ith point can
     * be moved to the right by a distance of dist[i].
     * 
     * Given weight and dist, find the minimum number of operations required to sort
     * the points by their weights.
     * 
     * Function Description 🥑
     * 
     * Complete the function getMinOperations2 in the editor -
     * 
     * GetMinOperations2 has the following arguments -
     * 
     * int weights[n]: the weights of the points
     * int dist[n]: the distances the points can move
     * Returns
     * 
     * long int: the min num of operations to sort the points.
     */

    public static void main(String[] args) {
        int[] weight = new int[]{2, 4, 3, 1};
        int[] dist = new int[]{2, 6, 3, 5};
        long operation = getMinOperations2(weight, dist);
        System.out.println("operation = " + operation);
    }

    public static long getMinOperations2(int[] weight, int[] dist) {
        int n = weight.length;
        //reate data structure to store weights and corresponding dist
        List<int[]> points = new ArrayList<>();
        for(int i = 0; i < n; i++){
            int[] point = new int[]{weight[i], i, dist[i]};
            points.add(point);
        }

        //Sort points by weight
        points.sort(Comparator.comparingInt(a -> a[0]));
        Set<Integer> occupanied = new HashSet<>();

        int last_filled = -1;
        long min_operation = 0;
        for(int i = 0; i < n; i++){
            int[] point = points.get(i);
            int point_x = point[1];
            int operation = point[2];
            while(occupanied.contains(point_x) || point_x < last_filled){
                //find new place to fill
                point_x += operation;
                min_operation++;
            }
            
            last_filled = point_x;
            point[1] = point_x;
            occupanied.add(point_x);
        }
        return min_operation;
    }

}
