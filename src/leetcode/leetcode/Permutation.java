package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Permutation {
    private List<List<Integer>> result;

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,2};
        Permutation p = new Permutation();
        p.permuteUnique(nums);

    }
        
    public List<List<Integer>> permute(int[] nums) {
        result = new ArrayList<>();
        
        permuteSwap(nums, 0);
        
        return result;
    }
    
    private void permuteSwap(int[] nums, int currIndex){
        if(currIndex == nums.length - 1){
            addCurrNums(nums);
            return ;
        }
        
        for(int i = currIndex; i < nums.length; i++){
            swap(nums, currIndex, i);//do swap
            permuteSwap(nums, currIndex + 1);//recursive call
            swap(nums, currIndex, i);//undo swap
        }
    }
    
    private void swap(int[] nums, int i, int j){
        if(nums.length == 0 || i < 0 || j < 0) return;
        if(i >= nums.length || j >= nums.length) return;
        
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    private void addCurrNums(int[] nums){
        List<Integer> currList = new ArrayList<Integer>();
        for(int j = 0; j < nums.length; j++){
            currList.add(nums[j]);
        }
        result.add(currList);
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        result = new ArrayList<List<Integer>>();
        
        permuteUnique(nums, new boolean[nums.length], new ArrayList<Integer>());
        
        return result;
    }
    
    private void permuteUnique(int[] nums, boolean[] numsVisited, List<Integer> currList){
        //baseline - when is done
        if(currList.size() == nums.length){
            result.add(new ArrayList<Integer>(currList));
            return ;
        }
        
        for(int i = 0; i < numsVisited.length; i++){
            if(!numsVisited[i]){
                if(i > 0 && nums[i] == nums[i-1]) {
                    continue;
                }
                else {
                    currList.add(nums[i]);
                    numsVisited[i] = true;
                
                    permuteUnique(nums, numsVisited, currList);
                
                    currList.remove(currList.size() - 1);
                    numsVisited[i] = false;
                }
                
            }
        }
    }
}
