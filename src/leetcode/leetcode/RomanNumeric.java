package leetcode;

import java.util.*;


public class RomanNumeric {
    /*
    Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
    */
    public static void main(String[] args) {
        
    }

    public String intToRoman(int num){
        /*
        I is 1, V is 5, X is 10, L is 50, X is 100, D is 500, M is 1000
        */
        String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LX", "LXX", "LXXX", "XC"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] thousands = {"", "M", "MM", "MMM"};
        
        //need to break the num into digits
        //map the digit in different place value to a Roman string, for example, 20 -> 2 in tens is mapped to XX
        /*
        one: num % 10
        ten: (num % 100) / 10
        hundred: (num % 1000) / 100
        thousands: (num / 1000)
         */
        return thousands[num / 1000] +
               hundreds[(num%1000)/100] +
               tens[(num % 100) / 10] +
               ones[num % 10];

        //StringBuilder strBuilder = new StringBuilder();
        //return strBuilder.toString();
    }


    public int romanToInt(String s) {
        int output = 0;
        Map<Character, Integer> romanMap = new HashMap<Character, Integer>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        int prevInt = Integer.MAX_VALUE;
        for(int i = 0; i<s.length(); i++){
            int currInt = romanMap.get(s.charAt(i));
            if (prevInt < currInt){
                //like, IX should be X - I. so need to remove the prevInt twice, one is for the previous one added, another is for the X - I
                output = output - prevInt *2 + currInt;
            } else output = output + currInt;
            prevInt = currInt;
        }
        return output;
    }
}