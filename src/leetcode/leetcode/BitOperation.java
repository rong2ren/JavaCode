package leetcode;

public class BitOperation {

    static int next(int x)
    {
        int sbit = x&~(x-1);
        int hbits = x+sbit;
        int lbits = x^hbits;
        lbits /= sbit;
        lbits >>= 2;
        x = hbits|lbits;
        return x;
    }

    public static void main(String[] args) {
        
        int numberLength = 4;
        int bitsLength = 2;
        for(int num = (int)Math.pow(2, bitsLength) -1; num < (int)Math.pow(2, numberLength + 1); num = num << 1){
            if(num > (int)Math.pow(2, numberLength)){
                System.out.println(Integer.toBinaryString(num + 1).substring(1));
            } else {
                System.out.println(Integer.toBinaryString(num));
            }
            
        }
        System.out.println("----");
        int x = (int)Math.pow(2, bitsLength) -1;
        while(x < (int)Math.pow(2, numberLength)){
            String binaryString = Integer.toBinaryString(x);
            String bitMask = String.format("%" + numberLength + "s", binaryString).replace(' ', '0');
            System.out.println(bitMask);
            x = next(x);
        }
        

        
        int n = 4;

        /* first way */
        int nthBit = 1 << n;
        
        for (int i = 0; i < (int)Math.pow(2, n); ++i) {
            // generate bitmask, from 0..00 to 1..11
            String bitmask = Integer.toBinaryString(i | nthBit).substring(1);
            //System.out.println(i | nthBit);
            //System.out.println(Integer.toBinaryString(i | nthBit));
            //System.out.println(bitmask);
        }


        /* 2nd way */
        //System.out.println((int)Math.pow(2, n));
        //System.out.println((int)Math.pow(2, n + 1));
        for (int i = (int)Math.pow(2, n); i < (int)Math.pow(2, n + 1); ++i) {
            // generate bitmask, from 0..00 to 1..11
            //String bitmask = Integer.toBinaryString(i).substring(1);
        }
    }
}
