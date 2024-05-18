package test;

public class Fibonacci {
    public static void main(String[] args) {
        Fibonacci f = new Fibonacci();
        f.printFibonacci(20);
    }
    public void printFibonacci(int n){
        int first = 0;
        int second = 1;
        System.out.print(first + " " + second + " ");
        for(int i = 2; i < n; i++){
            int third = first + second;
            System.out.print(third + " ");
            first = second;
            second = third;
        }
        System.out.println("");
    }
}
