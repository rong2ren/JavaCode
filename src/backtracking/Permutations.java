package backtracking;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    private List<List<Integer>> result;
    
    /*
     * Approach 1: decision tree with backtracking
     */
    public List<List<Integer>> permute(int[] nums) {
        result = new ArrayList<>();
        
        findPermutation(nums, new boolean[nums.length], new ArrayList<Integer>());    
        return result;
    }
    
    private void findPermutation(int[] nums, boolean[] numsVisited, List<Integer> currList){
        if(currList.size() == nums.length){
            //find one
            result.add(new ArrayList<Integer>(currList));
            return;
        }
        
        for(int i = 0; i < numsVisited.length; i++){
            if(!numsVisited[i]) {
                numsVisited[i] = true;
                currList.add(nums[i]);
                
                findPermutation(nums, numsVisited, currList);
                
                currList.remove(currList.size()-1);
                numsVisited[i] = false;
            }
        }
        
    }
}
