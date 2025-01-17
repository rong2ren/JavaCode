package amazon;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SmallerItem {

    public static void main(String[] args) {
        int[]items = new int[]{1, 2, 3, 2, 4, 1};
        int[] start = new int[]{2,0};
        int[] end = new int[]{4,0};
        int[] query = new int[]{5,3};
        int[] result = getSmallerItems(items, start, end, query);
        for(int i = 0; i < query.length; i++){
            System.out.println(query[i] + ":" + result[i]);
        }
    }

    public static int[] getSmallerItems(int[] items, int[] start, int[] end, int[] query) {
        int[] result = new int[query.length];
        int n = start.length;//number of picks
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for(int i = 0; i < n; i++){
            for(int j = start[i]; j <= end[i]; j++){
                //pick item[j]
                frequencyMap.put(items[j], frequencyMap.getOrDefault(items[j],0) + 1);
            }
        }
        Arrays.sort(items);
        //process the queries
        for(int i = 0; i < query.length; i++){
            int val = query[i];
            //binary search
            int idx = binarySearch(items, val);//need to improve the binary search - instead of binary search, you can use heap
            int count = 0;
            if(idx >= 0){
                //from 0 to idx
                
                for(int j = 0; j < idx; j++) count+=frequencyMap.get(items[j]);
            }
            result[i] = count;
        }

        return result;
    }


    // Binary search to find the position in the sorted list of items
    public static int binarySearch(int[] sortedArray, int target) {
        int left = 0, right = sortedArray.length - 1;
        if(sortedArray[left] > target) return -1;
        if(sortedArray[right] <= target) return right;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (sortedArray[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
