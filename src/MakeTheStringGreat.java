/**
 * Given a string s of lower and upper case English letters.
 *
 * A good string is a string which doesn't have two adjacent characters s[i] and s[i + 1] where:
 *
 * 0 <= i <= s.length - 2
 * s[i] is a lower-case letter and s[i + 1] is the same letter but in upper-case or vice-versa.
 * To make the string good, you can choose two adjacent characters that make the string bad and remove them. You can keep
 * doing this until the string becomes good.
 *
 * Return the string after making it good. The answer is guaranteed to be unique under the given constraints.
 *
 * Notice that an empty string is also good.
 * */
public class MakeTheStringGreat {
    public static String makeGood(String s) {
        boolean flag = true;
        int n = s.length();

        while (flag){
            flag = false;
            for (int i = 0; i < n-1; i++) {
                if(s.charAt(i)==s.charAt(i+1)+32 || s.charAt(i+1)==s.charAt(i)+32){
                    s = s.substring(0,i)+s.substring(i+2);
                    n = n-2;
                    flag = true;
                }
            }
        }


        return s;
    }

    public static void main(String[] args) {
        System.out.println(makeGood("leEeetcode"));
    }

}
