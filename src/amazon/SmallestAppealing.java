package amazon;

public class SmallestAppealing {
/*
 * A manager at Amazon is responsible for organizing a thrilling online shopping event for a tech festival, and they have come up with an exciting concept. In this game, users are presented with a sequence of digits representing a product code. The challenge is to find a new product code that is equally attractive or even more attractive than the original one.

Let's call an integer, represented in its decimal notation in order from left to right, b[1], b[2], b[3], ... b[n] attractive if b[i]=b[i+k], for each i such that 1 ≤ i ≤ n-k, where n and k are positive integers. For example, if k = 2, "252525" is attractive but "245254" is not.

Your task is to generate a new product code, represented by string new_code such that it is greater than or equal to the original product code old_code.

Formally, Given a string old_code of size n representing the digits in the decimal notation and integer k, find a string new_code, representing the digits in decimal notation, which satisfies old_code ≤ new_code and which also is attractive, given that there are no leading zeros in old_code and new_code.
 */
    public static void main(String[] args) {
        String old_code = "12345678";
        int k = 4;
        System.out.println(findSmallestAppealing(old_code, k));
    }

    public static String findSmallestAppealing(String old_code, int k) {
        //handle base case / boundary?
        int n = old_code.length();
        if(k == 0 || k > n) return old_code;

        StringBuilder new_code = new StringBuilder();

        //try to construct the new_code base on the rule
        for(int i = 0; i < n; i++){
            if(i < k) new_code.append(old_code.charAt(i));
            else {
                new_code.append(old_code.charAt(i % k));
            }
        }

        //if the new_code < old_code, start the modfication process from right to left
        if(!isValid(old_code, new_code.toString())){
            for(int i = n - 1; i >= 0; i++){
                if(new_code.charAt(i) < old_code.charAt(i)){
                    //j is the first character that < k and
                    int j = i % k;
                    int new_num = new_code.charAt(i) - '0' + 1;
                    while(j < n){
                        new_code.setCharAt(j, (char)(new_num + '0'));
                        j = j + k;
                    }
                    //check if the new_code is valid now, if it is valid, break out the modification process
                    if(isValid(old_code, new_code.toString())) break;
                }
            }
        }

        return new_code.toString();
    }

    private static boolean isValid(String old_str, String new_str){
        int old_num = Integer.parseInt(old_str);
        int new_num = Integer.parseInt(new_str);
        if(old_num <= new_num) return true;
        else return false;
    }
}
