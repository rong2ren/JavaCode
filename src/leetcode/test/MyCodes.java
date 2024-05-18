package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyCodes {
    public static void main(String[] args) {
        
        /*
        String str = "acabacacd";
        int[] lps = getLPS(str);
        for(int i = 0; i < lps.length; i++){
            System.out.println(str.charAt(i) + "-" + i + ":" + lps[i]);
        }
         */
        
         int[] sum = {-1,0,1,2,-1,-4};
         threeSum(sum);
        
    }

    private static int[] twoSum(int[] nums, int target) {
        
        Map<Integer, Integer> map = new HashMap<>();
//first traverse: Build the hash table
        for(int i = 0; i < nums.length; i++){
            map.put(nums[i], i);
            System.out.println(nums[i]);
        }
        

//second traverse: Find the complement        
        for (int i = 0; i < nums.length; i++){
            int complement = target - nums[i];
            if(map.containsKey(complement)){
                int j = map.get(complement);
                if (i != j){
//this check is important for nums = [3,2,4] and target = 6. otherwise it will return [0,0]
                    return new int[]{i, j};
                }
            }
        }
        //in case there is no sultion, we'll just return null;
        return null;
    }

    private static int binary_search(int[] nums, int start, int end, int target){
        if(start < 0 || end >= nums.length || nums.length <= 0) return -1;
        
        while(start <= end){
            int middle = (end + start)/2;
            if(nums[middle] < target) start = middle + 1;//target is between middle+1, end
            else if(nums[middle] > target) end = middle - 1;//target is between start, middle - 1
            else return middle;
        }
        
        return -1;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums.length < 3) return result;

        Arrays.sort(nums);

        for(int left = 0; left < nums.length - 2; left++){
            //since the array is sorted now. if the left is >0, there is no way to find 3 numbers with sum = 0
            if(nums[left] > 0) break;

            for(int middle = left + 1; middle < nums.length - 1; middle++){
                int complement = 0 - nums[left] - nums[middle];
                System.out.println("trying to find " + complement);
                int right = binary_search(nums, middle + 1, nums.length - 1, complement);
                System.out.println("find " + complement + " in the array:" + right);
                if(right > middle){
                    //find it
                    result.add(Arrays.asList(nums[left], nums[middle], nums[right]));
                }
                
                //skip duplicate middle number that has been processed
                if(middle > left + 1 && nums[middle] == nums[middle - 1]) continue;
                
            }
            //skip duplicate left number that has been processed
            if(left > 0 && nums[left] == nums[left - 1]) continue;
            
        }

        return result;
    }

    private static int[] getLPS(String p) {
        int size = p.length();
        int[] lps = new int[size];
        //m: it is the index of the element before which prefix = suffix
        //prefix = suffix till m - 1
        //m can also be viewed as index of first mismatch
        int m = 0;
        int i = 1;//for i = 0, it will always be 0, so starting from index 1
        while (i < size){
            if(p.charAt(i) == p.charAt(m)){
                m++;
                lps[i] = m;
                i++;
            } else {
                if(m == 0){
                    //m == 0, we move to the next letter
                    //there is no any prefix found which = suffix for index i
                    lps[i] = 0;
                    i++;
                } else {
                    //when there is a mismatch, we will check the index of previous possible prefix
                    m = lps[m - 1];//update m
                }
            } 
        }
        return lps;
    }


    private static boolean kmp(String t, String p){
        int[] lps = getLPS(p);
        int i = 0, j = 0;
        
        while(i < t.length()){
            if(t.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            }
            else {
                if(j > 0) j = lps[j-1];
                else i++;
            }
            
            if(j == p.length()) return true;
        }
        
        return false;
    }
}
