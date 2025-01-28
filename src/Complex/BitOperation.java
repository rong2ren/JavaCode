package Complex;

public class BitOperation {

    public static void main(String[] args) {
        
    }

    /*
     * Developers at Amazon are working on a new algorithm using the Bitwise XOR operation.
Given an array arr of even length n, Developers can perform an operation on a given array which is defined below as many times as necessary:
Choose two indices L and R, where 0 ≤ L ≤ R < N.
Let x be the bitwise XOR of all elements of the subarray represented by indices L and R of the given array.
Assign all elements of the chosen subarray to x.
Given an integer array arr[], find the minimum number of elements of the required to make all operations given array equal to zero.
Note: Bitwise XOR for an array of numbers is determined by counting each bit position across all numbers in the array. If the total count of set bits at a bit-position is odd, the resulting bit in output is set to 1. Otherwise, the resulting bit is set to 0..
     */
    public int getMinMoves(int[] arr) {
        //The XOR of two identical numbers is zero
        //The XOR of any number with zero is that number
        //Find the XOR of the entire array. If the XOR is zero, then no operations are needed. 
        //Otherwise, perform operations to make the XOR zero.
        /*
         * 
         * it can be proven that the maximum number of operations will be always 2, 
         * since in first operation I can just take XOR to the whole array and assign the elements to that result, 
         * and then in the second operation I'll just take the XOR of the whole array again and since that the n (which is the number of the elements in the array) are even then It'll output 0. 
         * So basically xor the entire array and checking if it’s already 0 or not if it is then 1 operation otherwise 2.
         */

         return 2;

    }
}
