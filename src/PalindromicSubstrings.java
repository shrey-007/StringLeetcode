/**
 * Given a string s, return the number of palindromic substrings in it.
 *
 * A string is a palindrome when it reads the same backward as forward.
 *
 * A substring is a contiguous sequence of characters within the string.
 * */
public class PalindromicSubstrings {
    // we have already solved longest palindromic substring using axis orbit method, do same here
    public int countSubstrings(String s) {

        int count = 0;
        int n = s.length();

        for (int axis = 0; axis < n; axis++) {
            for (int orbit = 0; orbit < n; orbit++) {
                int leftCharIndex = axis-orbit;
                int rightCharIndex = axis+orbit;
                if(leftCharIndex<0 || rightCharIndex>=n){break;}
                char rightChar = s.charAt(rightCharIndex);
                char leftChar = s.charAt(leftCharIndex);
                if (rightChar!=leftChar){break;}
                count++;
            }
        }


        for (double axis = 0.5; axis <= n-1.5; axis++) {
            for (double orbit = 0.5; orbit < n; orbit++) {
                int leftCharIndex = (int)(axis-orbit);
                int rightCharIndex = (int)(axis+orbit);
                if(leftCharIndex<0 || rightCharIndex>=n){break;}
                char rightChar = s.charAt(rightCharIndex);
                char leftChar = s.charAt(leftCharIndex);
                if (rightChar!=leftChar){break;}
                count++;
            }
        }

        return count;
    }
}
