package backtracking;

import java.util.ArrayList;
import java.util.List;

public class Subsets {


    /*
     * ### Approach 1: Cascading
     * [ ] → [ ][1] → [ ] [1] [2] [1, 2] → [ ] [1] [2] [1, 2] [3] [1,3] [2,3] [1,2,3]
     */
    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();        
        
        for(int i = 0; i < nums.length; i++){
            int num = nums[i];
            int size = result.size();
            for(int j = 0; j < size; j++){
                List<Integer> one_set = new ArrayList<Integer>(result.get(j));
                one_set.add(num);
                result.add(one_set);
            }
            //always add itself as a set
            List<Integer> own_set = new ArrayList<Integer>();
            own_set.add(num);
            result.add(own_set);
        }
        
        //add an empty set to the result
        List<Integer> empty_set = new ArrayList<Integer>();
        result.add(empty_set);
        
        return result;
    }


    /*
     * Approach 2: all possible combinations of all possible lengths, from 0 to n.
     */
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        //iterate over all possible length: from 1 to nums.length;
        for(int curr_length = 1; curr_length <= nums.length; curr_length++){
            //for each length, always start from index 0 and a empty ArrayList
            backtrack(0, new ArrayList<Integer>(), nums, curr_length, result);
        }
        
        //add an empty set to the result (length: 0)
        result.add(new ArrayList<Integer>());
        
        return result;
    }

    private void backtrack(int first, ArrayList<Integer> curr_list, int[] nums, int curr_length, List<List<Integer>> result){
        if(curr_list.size() == curr_length){
            //the combination is done - achieve the required length
            result.add(new ArrayList<Integer>(curr_list));
            return;
        }
        //iterate from first to the end of nums array -> as the starting index
        for(int i = first; i < nums.length; i++){
            //add i to the curr_list
            curr_list.add(nums[i]);
            //call backtrack on index i+1, so add num to the curr_list until desired length
            backtrack(i+1, curr_list, nums, curr_length, result);
            //since we find the desired length starting with index first
            //we need to search for other possible combination
            //by removing nums[first] from the result
            curr_list.remove(curr_list.size()-1);
        }
    }

    /*
     * Approach 3: Bit Manipulation
     * The idea is that we map each subset to a bitmask of length n, where 1 on the ith position in bitmask means the presence of nums[i] in the subset, and 0 means its absence.
     */
    public List<List<Integer>> subsets3(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>(); 
        
        int n = nums.length;
        for(int i = (int)Math.pow(2, n); i < (int)Math.pow(2, n+1); i++){
            //generate bitmask, from 0..00 to 1..11
            String bitmask = Integer.toBinaryString(i).substring(1);
            List<Integer> currList = new ArrayList<>();
            for(int j = 0; j < n; j++){
                if(bitmask.charAt(j) == '1') currList.add(nums[j]);
            }
            result.add(currList);
            
        }
        
        return result;
    }
}
