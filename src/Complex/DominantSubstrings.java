package Complex;

public class DominantSubstrings {
/*
 * A team of data analysts at Amazon is working to identify patterns in data. During their analysis, they discovered a category of strings they call a 'dominant string':

The string has an even length.
The string contains at least one character with a frequency equal to exactly half the length of the string.
Given a string s of length n, determine how many of its substrings are dominant strings.
 */
    public static void main(String[] args) {
        System.out.println("dominate strings count=" + countDominantSubstrings("aaaabd"));
        System.out.println(countDominantSubstrings2("aaaabd"));
    }

    public static int countDominantSubstrings2(String s) {
        //similar to leetcode 3325: count substring where at least one character appears at least k time
        // total number of substrings - number of substrings where all character appears at most k - 1 times

        //this problem can translate into 
        //at least one character with a frequency equal to exactly half the length of the string.
        //total number of substrings - number of substrings where all character either more than half or less than half
        //sliding window
        int n = s.length();
        int count = 0;
        int[] freq = new int[26];

        int number_of_substrings = n * (n - 1) / 2;
        for(int i = 0, j = 0; j < n; j++){
            //i: window start
            //j: window end
            //iterate using window end - j
            
            int freq_index = s.charAt(j) - 'a';
            freq[freq_index]++;

            System.out.println("current window:" + i + "-" + j + " window_size=" + (j-i+1));
            System.out.println("current char:" + s.charAt(j) + " freq=" + freq[freq_index]);
            int window_size = j-i+1;
            if(window_size % 2 == 0){
                boolean valid_window = false;
                for(int z = i; z <= j; z++){
                    if(freq[s.charAt(z) - 'a'] == window_size / 2){
                        valid_window = true;
                        count++;
                        break;
                    }
                }
                //shrink the window by window start
                if(valid_window){
                    freq[s.charAt(i) - 'a']--;
                    i++;
                    System.out.println("shrink to " + i + "-" + j);
                }
            } 
            number_of_substrings -= (j - i + 1);
            /* 
            while(freq[freq_index] > window_size / 2){
                //shrink the window by window start
                freq[s.charAt(i) - 'a']--;
                i++;
            }*/
            System.out.println("substring:" + s.substring(i, j + 1));
        }
        return count;
    }

    public static int countDominantSubstrings(String s) {
        int n = s.length();
        int count = 0;

        //Dynamic Programming
        //Create a prefix sum array for character frequencies
        //prefix_sum[i][c]: holds the frequency of character c in the substring s[0:i].
        int[][] prefix_freq = new int[n + 1][26];

        for(int i = 0; i < n; i++){
            int char_index = s.charAt(i) - 'a';
            for(int j = 0; j < 26; j++){
                prefix_freq[i + 1][j] = prefix_freq[i][j] + ((char_index == j)? 1 : 0);
            }
        }

        //check all even length
        for(int length = 2; length <= n; length = length + 2){
            //System.out.println("\nchecking length=" + length);
            int half_length = length / 2;
            for(int start = 0; start <= n - length; start++){
                int end = start + length;
                //System.out.println("substring=" + s.substring(start, end) + "-" + " start=" + start + " end=" + end);
                for(int character = 0; character < 26; character++){
                    /*
                     * The difference prefix_freq[end][char] - prefix_freq[start][char] gives the frequency of the character in the substring s[start:end].
                    
                    if(character == 0 || character == 1 || character == 8){
                        System.out.println("frequency of " + (char)(character + 'a') + "=" + (prefix_freq[end][character] - prefix_freq[start][character]));
                    } */
                    if(prefix_freq[end][character] - prefix_freq[start][character] == half_length){
                        count++;
                        System.out.println("substring=" + s.substring(start, end) + "- dominate -" + (char)(character + 'a') + " frequency=" + half_length);
                        break;
                    }
                }
            }
        }

        return count;
    }
}
