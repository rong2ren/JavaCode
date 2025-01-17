package amazon;

public class SpecialString {

    public static void main(String[] args) {
        String s = "abbada";
        System.out.println("original:" + s);
        System.out.println("changed to:" + getSpecialString(s));
    }

    /*
     * evelopers at Amazon are working on a text generation utility for one of their new products.
Currently, the utility generates only special strings. A string is special if there are no matching adjacent characters. Given a string s of length n, generate a special string of length n that is lexicographically greater than s. If multiple such special strings are possible, then return the lexicographically smallest string among them.
Notes:
Special String: A string is special if there are no two adjacent characters that are the same.
     */
    public static String getSpecialString(String s) {
        //dp[i]: special string from 0 to i where no adjacent characters are the same, store the last character
        //dp[i+1]: append a character to the special string from 0 to i
        //then it cannot be the same as s.charAt(i)
        int n = s.length();
        
        char[] dp = new char[n];
        char[] characters = s.toCharArray();
        dp[0] = characters[0];
        boolean changed = false;

        for(int i = 1; i < n; i++){
            char next_char = characters[i];
            System.out.println("next char: " + next_char + " preivous char:" + dp[i-1]);
            if(next_char == dp[i - 1]){
                //we need to find a new character for this index i;
                //2 cases
                // firs time you encounter
                System.out.print("- duplicate!");
                if(!changed){
                    dp[i] = (char)(characters[i] + 1);
                    changed = true;
                } else {
                    for(int j = 0; j < 26; j++){
                        if(j != (characters[i] - 'a')){
                            dp[i] = (char)(j + 'a');
                            break;
                        }
                    }
                }
                System.out.println(characters[i] + " changed to " + dp[i]);
            } else {
                System.out.print("- NOT duplicate!");
                if(!changed) dp[i] = characters[i];
                else {
                    for(int j = 0; j < 26; j++){
                        if(j <= (characters[i] - 'a') && j != dp[i-1] - 'a'){
                            dp[i] = (char)(j + 'a');
                            break;
                        }
                    }
                }
                System.out.println(characters[i] + " changed to " + dp[i]);
            } 
        }
        
        return new String(dp);
    }
}
