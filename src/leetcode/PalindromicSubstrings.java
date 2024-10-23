package leetcode;

public class PalindromicSubstrings {
    
    /*
    Given a string s, return the number of palindromic substrings in it.

    A string is a palindrome when it reads the same backward as forward.

    A substring is a contiguous sequence of characters within the string.
    */
    public static void main(String[] args) {
        PalindromicSubstrings ps = new PalindromicSubstrings();
        System.out.print("count of PalindromicSubstrings: " + ps.countSubstrings2("aaa"));
    }

    

    //first solution: two loops, find all substring and check if the substring is a palindrome
    public int countSubstrings(String s) {
        int numOfPalindrome = 0;
        StringBuilder strBuilder = new StringBuilder(s);

        for(int i = 0; i < s.length(); i++){
            for (int j = i+1; j <= s.length(); j++){
                if(isStringPalindrome(strBuilder.substring(i, j))) numOfPalindrome++;
            }
            
        }
        return numOfPalindrome;
    }

    private boolean isStringPalindrome(String s){
        int start;
        int end;
        if(s.length() % 2 != 0){
            start = s.length()/2;
            end = start;
        } else {
            start = s.length()/2-1;
            end = start + 1;
        }
        System.out.println("checking " + s + " with start=" + start + " end=" + end);
        while(end < s.length() && start >= 0){
            if(s.charAt(start) == s.charAt(end)){
                start--;
                end++;
            } else return false;
        }
        System.out.println("It is palindrome!");
        return true;
    }



    //2nd solution: one loop
    public int countSubstrings2(String s){
        int numOfPalindrome = 0;
        for (int mid = 0; mid < s.length(); mid++){
            numOfPalindrome += countPalindromeFromMidPoint(s, mid, mid);

            if(mid != s.length() - 1 && s.charAt(mid) == s.charAt(mid+1)){
                numOfPalindrome += countPalindromeFromMidPoint(s, mid, mid+1);
            }
            
        }
        return numOfPalindrome;
    }

    private int countPalindromeFromMidPoint(String s, int start, int end){
        int output = 0;
        while(start >= 0 && end < s.length()){
            if(s.charAt(start) == s.charAt(end)){
                start--;
                end++;
                output++;
            } else break;
        }
        return output;
    }

}
