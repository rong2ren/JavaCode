package Complex;

public class MinErrors {
/*
 * Amazon's database doesn’t support very large numbers, so numbers are stored as a string of binary characters, '0' and '1'. Accidentally, a '!' was entered at some positions and it is unknown whether they should be '0' or '1'.

The string of incorrect data is made up of the characters '0', '1' and '!' where '!' is the character that got entered incorrectly. '!' can be replaced with either '0' or '1'. Due to some internal faults, some errors are generated every time '0' and '1' occur together as '01' or '10' in any subsequence of the string. It is observed that the number of errors a subsequence '01' generates is x, while a subsequence '10' generates y errors.

Determine the minimum total errors generated. Since the answer can be very large, return it modulo 109+7.

Note: A subsequence of a string is obtained by omitting zero or more characters from the original string without changing their order.

Hint: It can be proved that (a + b) % c = ((a% c) + (b % c)) % c where a, b, and c are integers and % represents the modulo operation.
 */
    public static void main(String[] args) {
        System.out.println("min error=" + getMinErrors("01!0", 2, 2));
    }

    public static int getMinErrors(String errorString, int x, int y) {
        char[] characters = errorString.toCharArray();

        int index = -1;
        for(int i = 0; i < characters.length; i++){
            if(characters[i] == '!'){
                index = i;
            }
        }

        characters[index] = '0';
        int error1 = computerError(characters, x, y);
        System.out.println(String.valueOf(characters) + " - " + error1);
        characters[index] = '1';
        int error2 = computerError(characters, x, y);
        System.out.println(String.valueOf(characters) + " - " + error2);

        return Math.min(error1, error2) % (10^9 + 7);
    }

    private static int computerError(char[] characters, int x, int y){
        int n = characters.length;
        int zero_count = 0, one_count = 0;
        int[] prefix_zero_count = new int[n];//how many zeros are from 0 to i
        int[] prefix_one_count = new int[n];//how many ones are from 0 to i

        for(int i = 0; i < n; i++){
            if(characters[i] == '0') zero_count++;
            else one_count++;
            prefix_zero_count[i] = zero_count;
            prefix_one_count[i] = one_count;
        }

        int zero_one = 0, one_zero = 0;
        for(int i = 1; i < n; i++){
            if(characters[i] == '0'){
                //check the number of 1s at this postion - 10
                one_zero += prefix_one_count[i];
            } else {
                //check the number of 0s at this position - 01
                zero_one += prefix_zero_count[i];
            }
        }

            return zero_one * x + one_zero * y;
    }

}
