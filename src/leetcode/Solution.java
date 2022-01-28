package leetcode;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        int input[] = new int[] {1, 2};
        
        Solution solution = new Solution();
        System.out.println("result:" + solution.detectCapitalUse("ggg"));
        
    }

    /*
    check if the number array contains duplicate
    */
    public boolean containsDuplicate(int[] nums) {
        
        Set<Integer> numsSet = new HashSet<Integer>();
        for(int i = 0; i < nums.length; i++){
            if(!numsSet.add(nums[i])){
                return true;
            }
        }

        return false;
    }

    /*
    We define the usage of capitals in a word to be right when one of the following cases holds:
    All letters in this word are capitals, like "USA".
    All letters in this word are not capitals, like "leetcode".
    Only the first letter in this word is capital, like "Google".
    Given a string word, return true if the usage of capitals in it is right.
    */
    public boolean detectCapitalUse(String word) {
        //boundary check
        if(word.length() < 2) return true;

        
        boolean firstCharUpp = Character.isUpperCase(word.charAt(0));
        boolean secondCharUpp = Character.isUpperCase(word.charAt(1));

        boolean uppercase = false;
        //check the first letter and second letter are both uppercase. if true, then the rest need to be uppercase.
        if (firstCharUpp && secondCharUpp) uppercase = true;
        //if both are lower case, then the rest need to be lowercase
        else if (!firstCharUpp && !secondCharUpp) uppercase = false;
        //if one is upper and one is lower, and the first char is not upper, return false
        else if (!firstCharUpp) return false;
    
        for (int i = 2; i < word.length(); i++){
            //check the following letter of the word, change the output
            if(Character.isUpperCase(word.charAt(i)) != uppercase) return false;
        }

        return true;
    }

    
}