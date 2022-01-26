package test;
// Java program to Illustrate Comparison of Two Strings
 
// Main class
public class StringTest {
 
    // Main driver method
    public static void main(String[] args)
    {
        // Custom input strings to compare
        String s1 = "Ram";
        String s2 = "Ram";
        String s5 = "Shyam";
 
        String s3 = new String("Ram");
        String s4 = new String("Ram");
 
        // Checking whether strings are equal or not
        // with help of equals() method
        System.out.println(
            " Comparing strings with equals:");
        System.out.println(s1.equals(s2));
        System.out.println(s1.equals(s3));
        System.out.println(s1.equals(s5));
 
        String nulls1 = null;
        String nulls2 = null;
 
        // NullPointerException will be throws if
        // we try to compare nulls strings
        // System.out.println(nulls1.equals(nulls2));
 
        // Comparing strings using == operator
        System.out.println(" Comparing strings with ==:");
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s3 == s4);
        System.out.println(nulls1 == nulls2);
 
        // Comparing strings via compareTo() method
        System.out.println(
            " Comparing strings via compareTo() Method :");
        System.out.println(s1.compareTo(s3));
        System.out.println(s1.compareTo(s5));
 
        // NullPointerException if we try to compare strings
        // with usage of compareTo() method
        // System.out.println(nulls1.compareTo(nulls2));
    }
}