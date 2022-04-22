package leetcode;

import java.util.Arrays;
import java.util.LinkedList;


public class MergeInterval {
    public static void main(String[] args) {
        int[][] input = new int[][]{new int[]{1,3},new int[]{2,6},new int[]{8,10},new int[]{15,18}};
    }


    /**
     * Given an array of intervals where intervals[i] = [starti, endi], 
     * merge all overlapping intervals, 
     * and return an array of the non-overlapping intervals that cover all the intervals in the input.
     * 
     * sort the input: we can use TreeMap or ArrayList
     */
    public static int[][] merge(int[][] intervals) {
        //sort the intervals array
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        LinkedList<int[]> mergedList = new LinkedList<int[]>();

        for(int[] interval : intervals){
            if(mergedList.isEmpty() || interval[0] > mergedList.getLast()[1]){
                //if current starting point is bigger than last end point: no overlap, simple add to the linkedlist
                mergedList.add(interval);
            } else {
                mergedList.getLast()[1] = Math.max(mergedList.getLast()[1], interval[1]);
            }
        }

        return mergedList.toArray(new int[mergedList.size()][]);
    }
}
