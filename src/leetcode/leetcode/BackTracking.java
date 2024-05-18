package leetcode;
import java.util.*;

public class BackTracking {
    public static void main(String[] args) {
        int[] input = new int[]{1,2,3};
        BackTracking bt = new BackTracking();
        bt.subsetsBackTrack(input);
    }

    public List<List<Integer>> subsets(int[] nums) {
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

    List<List<Integer>> output = new ArrayList<List<Integer>>();

    public List<List<Integer>> subsetsBackTrack(int[] nums) {
        //iterate over all possible length: from 1 to nums.length;
        for(int curr_length = 2; curr_length <= nums.length; curr_length++){
            //for each length, always start from index 0 and a empty ArrayList
            backtrack(0, new ArrayList<Integer>(), nums, curr_length);
        }
        
        //add an empty set to the result (length: 0)
        output.add(new ArrayList<Integer>());
        
        return output;
    }
    
    private void backtrack(int first, ArrayList<Integer> curr_list, int[] nums, int curr_length){
        if(curr_list.size() == curr_length){
            //the combination is done
            output.add(new ArrayList<Integer>(curr_list));
            return;
        }
        
        //iterate from first to the end of nums array
        for(int i = first; i < nums.length; i++){
            //add i to the current combination
            curr_list.add(nums[i]);
            //call backtrack on index i+1, so it will add the following 
            //num from the array to the curr_list to form a length of curr_length
            backtrack(i+1, curr_list, nums, curr_length);
            curr_list.remove(curr_list.size()-1);
        }
    }
}
