package leetcode;
import java.util.*;

class Solution {
    class Event{
        int value;//left, right or query value
        int type;//0 - interval left ; 1 - queries ; 2 - interval right
        int additionValue;//length of the interval - interval left; index of the query - query; start value
        public Event(int value, int type, int additionValue){
            this.value = value;
            this.type = type;
            this.additionValue = additionValue;
        }
    } 
    public static void main(String[] args) {
        Solution s = new Solution();
        /*Queue<Event> minHeap = new PriorityQueue<Event>((a, b) -> (a.additionValue == b.additionValue) ? (a.value - b.value) : (a.additionValue - b.additionValue));        Event left = s.new Event(1, 0, 4);
        minHeap.add(left);

        Event right = s.new Event(4, 0, 1);
        minHeap.add(right);

        minHeap.removeIf(e -> (e.value <= right.additionValue));*/

        Set<Integer> lengthSortedSet = new TreeSet<>();
        lengthSortedSet.add(1);
        lengthSortedSet.add(2);
        lengthSortedSet.removeIf(l -> l == 1);
        
        TreeMap<Integer, Integer> lengthSortedMap = new TreeMap<>();


        //String[] wordList = new String[]{"hot","dot","dog","lot","log","cog"};
        //s.ladderLength("hit", "cog", Arrays.asList(wordList));  
        //int[] nums = new int[]{2,3,1,1,4}; 
        //System.out.println(s.jump(nums));
        //System.out.println(s.longestPalindrome("babad"));
        
        //int input = 43261596;
        //String output = Integer.toBinaryString(input);
        //System.out.println(output);

        

        int a = 20;
        int b = 30;
        s.getSum(a, b);
    }

    public int getSum(int a, int b) {   
        int sum = 0;
        int i = 0;
        while(a > 0 || b > 0){
            int bitA = a & 1;//get a's last digit
            int bitB = b & 1;//get b's last digit
            
            int bitSum = (bitA ^ bitB);
            
            if(bitA == bitB && bitA == 1){
                //if both bits are 1, then bitSum need to add the carry
                bitSum += (bitA & bitB) << 1;
            }
            
            //set the bit
            sum = sum | bitSum << i;
            
            i++;
            a = a >> 1;//remove last digit;
            b = b >> 1;//remove last digit
        }
        
        return sum;
    }

    public String longestPalindrome(String s) {
        if(s == null || s.length() < 1) return "";
        if(s.length() == 1) return s;
        
        /*
        Manacher's algorithm is designed to find the palindromic substrings with odd lengths only. 
        To use it for even lengths also, we tweak the input string by inserting the character "#" at the beginning and each alternate position after that (changing "abcaac" to "#a#b#c#a#a#c#").
        This tweak also works for the odd length string too!
        */
        
        /*
         If length of given string is n then its length after
         inserting n+1 "#" (aba -> #a#b#a#)
         (n) + (n+1)  = 2n+1
        */
        int strLen = s.length() * 2 + 1;
        char[] charArray = new char[strLen];
        charArray[0] = '#';
        for(int i = 0; i < s.length(); i++){
            charArray[i * 2 + 1] = s.charAt(i);
            charArray[i * 2 + 2] = '#';
        }
        
        
        int[] radiusFromCenter = new int[strLen];//the radiance of every palindrome centered on the charArray[i], not including the center
        radiusFromCenter[0] = 0;//no character on left side, so it is 0
        radiusFromCenter[strLen - 1] = 0;//no character on right side, so it is 0
        
        int palindromeCenter = 0;//longest palindrome center
        int palindromeRadius = 0;//longest palindrome radius
        
        int prevCenter = 0;
        int prevRadius = 0;
        
        for(int i = 1; i < strLen - 1; i++){
            
            if(i <= prevCenter + prevRadius){
                //mirrored i
                int mirrored = prevCenter - (i - prevCenter);
                radiusFromCenter[i] = radiusFromCenter[mirrored];
            } 
            
            //compute longest palindrome radinace from index i, but not including i;
            int radius = radiusFromCenter[i];
            while(i - (radius+1) >= 0 && i + (radius+1) < strLen){
                if(charArray[i - (radius+1)] == charArray[i - (radius+1) ]) {
                    radius++;
                }
                else break;
            }
            // Save the radius of the longest palindrome from current center in the array
            radiusFromCenter[i] = radius;
            
            //update prevCenter and prevRadius
            if(i + radiusFromCenter[i] > prevCenter + prevRadius) {
                prevCenter = i;
                prevRadius = radiusFromCenter[i];
            }
            
            //update the result
            if(charArray[i] != '#' && radiusFromCenter[i]/2 > palindromeRadius){
                //note: palindromeCenter & palindromeRadius are index in the original string
                palindromeCenter = (i-1)/2; //i in the charArray - > (i-1) / 2 in the origianl string s.
                palindromeRadius = (radiusFromCenter[i]-1)/2;
            }
        }
        
        for(int i = 0; i < radiusFromCenter.length; i++){
            System.out.print(radiusFromCenter[i]);
        }
        
        return s.substring(palindromeCenter - palindromeRadius, palindromeCenter + palindromeRadius + 1);
    }
}