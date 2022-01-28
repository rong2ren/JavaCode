package leetcode;

public class DetectCaptial  {
    //first solution
    public boolean detectCapitalUse(String word) {
        //boundary check
        if(word.length() < 2) return true;

        
        boolean firstCharUpp = Character.isUpperCase(word.charAt(0));
        boolean secondCharUpp = Character.isUpperCase(word.charAt(1));

        boolean uppercase = false;
        //check the first letter and second letter are both uppercase. if true, then the rest need to be uppercase.
        if (firstCharUpp && secondCharUpp) uppercase = true;
        //if both are lower case, then the rest need to be lowercase
        else if (!firstCharUpp && !secondCharUpp) uppercase = false;
        //if one is upper and one is lower, and the first char is not upper, return false
        else if (!firstCharUpp) return false;
    
        for (int i = 2; i < word.length(); i++){
            //check the following letter of the word, change the output
            if(Character.isUpperCase(word.charAt(i)) != uppercase) return false;
        }

        return true;
    }

    //second solution: the drawback is not quitting as soon as possible
    public boolean detectCapitalUse2(String word) {
        int numberOfCapitals = 0;
        for(int i = 0; i < word.length(); i++){
            if(Character.isUpperCase(word.charAt(i))) numberOfCapitals++;
        }

        if(numberOfCapitals == 0 || numberOfCapitals == word.length()) return true;
        else if(numberOfCapitals == 1 && Character.isUpperCase(word.charAt(0))) return true;

        return false;
    }
}
