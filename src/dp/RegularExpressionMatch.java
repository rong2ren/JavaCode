package dp;

public class RegularExpressionMatch {
    public static void main(String[] args) {
        String s = "aab";
        String p = "c*a*b";
        System.out.println("is match?" + isMatch(s, p));
    }

    public static boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m + 1][n + 1];
        //dp[i][j]: means if s.substring(0,i) matches pattern p.substring(0,j)

        dp[0][0] = true;//empty string matches empty pattern
        //first column: non-empty string, empty pattern -> not match
        for(int i = 1; i <= m; i++) dp[i][0] = false;
        printArray(dp);
        //first row: empty string, non-empty pattern, only if pattern has *
        for(int j = 2; j <= n; j++) dp[0][j] = p.charAt(j-1) == '*' &&  dp[0][j-2];
        printArray(dp);

        for(int j = 1; j <= n; j++){
            for(int i = 1; i <= m; i++){
                //check if s.substring(0,i) match p.substring(0,j)
                //assuming you already processed s.substring(0, i-1) with p.substring(0,j-1)
                char pattern = p.charAt(j-1);
                if(pattern == s.charAt(i-1) || pattern == '.'){
                    dp[i][j] = dp[i-1][j-1];
                } else if(pattern == '*'){
                    //c*: zero or one of c
                    if(dp[i][j - 2]) dp[i][j] = true;//case1: we take zero of c, then check dp[i][j-2]
                    else {
                        //case 2: we take one c, then need to check if c matches the character or if c is '.'
                        char preceding = p.charAt(j-2);
                        if(s.charAt(i-1) == preceding) dp[i][j] = dp[i-1][j];
                        else if(preceding == '.') dp[i][j] = dp[i-1][j];
                    } 
                }
                printArray(dp);
            }
        }

        return dp[m][n];
    }

    public static void printArray(boolean[][] array){
        System.out.println("-----------------");
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[0].length; j++){
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------");
    }
}
