package leetcode;
import java.util.*;

public class ThreeSum {

    public static void main(String[] args) {
        
    }

    public List<List<Integer>> calculateThreeSum(int[] nums) {
        //sort the input array first
        Arrays.sort(nums);
        
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        
        for(int firstItem = 0; firstItem < nums.length - 2; firstItem++){
            //only proces the 1st element and skip all the duplicate elements that appear after it (skips duplicates from the left). firstItem > 0 ensure this check is only made from the 2nd element onwards.
            if (firstItem > 0 && nums[firstItem] == nums[firstItem - 1]) continue;
            
            //the seoncd loop uses 2 pointers. secondItem and lastItem.
            int secondItem = firstItem + 1;
            int lastItem = nums.length - 1;
            int complement = nums[firstItem] * -1;
            
            while (secondItem < lastItem){
                if((nums[secondItem] + nums[lastItem]) > complement){
                    //too big, lastItem needs to be smaller
                    lastItem--;
                } else if((nums[secondItem] + nums[lastItem]) < complement){
                    //too small. firstItem needs to be bigger
                    secondItem++;
                } else {
                    //find the triplet
                    /*
                    List<Integer> triplets = new ArrayList<Integer>();
                    triplets.add(nums[firstItem]);
                    triplets.add(nums[secondItem]);
                    triplets.add(nums[lastItem]);
                    //Collections.sort(triplets);
                    resultList.add(triplets);
                    */
                    resultList.add(Arrays.asList(nums[firstItem], nums[secondItem], nums[lastItem]));               
                    
                    
                    //Skip all duplicates lastItem
                    lastItem--;                    
                    while((secondItem < lastItem - 1)&&(nums[lastItem] == nums[lastItem + 1])){
                       lastItem--;
                    }
                    //Skip all duplicates secondItem
                    secondItem++;
                    while((secondItem < lastItem - 1)&&(nums[secondItem] == nums[secondItem - 1])){
                       secondItem++;
                    }
                }
            }
            
        }
        
        return resultList;
    }
    
}
