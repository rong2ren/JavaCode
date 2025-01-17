package amazon;

import java.util.ArrayList;
import java.util.List;

public class OptimizeIdentifiers {
/*
 * In an Amazon inventory management, an operations analyst is dealing with a set of initial product identifiers represented by strings. The type of a product identifier is determined by the first and last letters in the identifier string, for example, the type of the identifier string "abddac" is "ac".
The analyst wants to optimize the product identifiers by performing a series of operations on the string to maximize the number of operations between the final and initial types.
Given a product identifier string s, the analyst can perform one operation at a time, involving the removal of either the first or last letter from the string.
Find the maximum number of operations they can perform on the string while ensuring that its type aligns with the initial string's type.
Note: The type of an empty string is "", and the type of a string with a single character, like "a", is "aa".
Function Description
Complete the function optimizeIdentifiers in the editor.
optimizeIdentifiers has the following parameter:
string s: the initial product identifiers.
Returns
int: the maximum number of operations the operations analyst can perform on the string, such that its type is equal to the initial string's type.
 */
    public static void main(String[] args) {
        int count = optimizeIdentifiers("hchc");
        System.out.println("count=" + count);
    }

    public static int optimizeIdentifiers(String s) {   
        if(s == null || s.length() <= 1) return 0;
        
        int n = s.length();
        char begin = s.charAt(0);
        char end = s.charAt(n - 1);
        System.out.println("identifier:" + begin + end);
        List<Integer> begin_index = new ArrayList<>();
        List<Integer> end_index = new ArrayList<>();

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == begin) begin_index.add(i);
            if(c == end) end_index.add(i);
        }
        int count = 0;
        for(int i : begin_index){
            for(int j : end_index){
                if(i <= j){
                    System.out.println("start=" + i + " end=" + j);
                    System.out.println("find one:" + s.substring(i, j + 1));
                    System.out.println("operation count=" + (n - j + i - 1));
                    System.out.println();
                    count = Math.max(count, (n - j + i - 1));
                }
            }
        }
        return count;
        //return dfs(s, begin, end, 0);
    }

    private static int dfs(String s, char begin, char end, int count){
        //base case
        int n = s.length();
        if(n == 0) return count;
        if(s.charAt(0) == begin && s.charAt(n-1) == end) {
            System.out.println("find one:" + s);
            count++;
        }

        //remove start character
        int remove_start = dfs(s.substring(1), begin, end, count);
        //remove end character
        int remove_end = dfs(s.substring(0, s.length() - 1), begin, end, count);
        //System.out.println(s + "-count=" + Math.max(remove_start, remove_end));
        return Math.max(remove_start, remove_end);
    }
}
