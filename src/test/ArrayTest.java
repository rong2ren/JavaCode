package test;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayTest {
    public static void main(String[] args) {
        int[] myArr = new int[]{4, 5, 6}; // interger array of 10 elements
        int[][] my2DArr = new int[3][3];
        int[] myArrWithInit = {1, 2, 3};

        for (int[] row: my2DArr){
            Arrays.fill(row, 99);
        }

        for(int i = 0; i < myArrWithInit.length; i++){
            System.out.print(myArrWithInit[i]);
            System.out.print(" ");
        }

        for(int j: myArr){
            System.out.print(j + " ");
        }

        ArrayList<String> sentence = new ArrayList<String>();
        sentence.add("mason");
        



    }
}
