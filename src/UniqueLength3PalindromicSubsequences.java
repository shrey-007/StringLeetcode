import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class UniqueLength3PalindromicSubsequences {

    // since it is subsequence problem , tr solving it using recursion
    public int countPalindromicSubsequence2(String s) {
        HashSet<String> hashSet = new HashSet<>();
        return func2(0,s,';',';',1,s.length(),hashSet);
    }

    public int func2(int index,String s,char prevChar,char prevPrevChar,int choice,int n,HashSet<String> hashSet){
        if(index==n){
            return 0;
        }

        // we have 2 cases take and not take
        // not take
        int faith1 = func2(index+1,s,prevChar,prevPrevChar,choice,n,hashSet);

        int faith2 = 0;
        // take
        // if we are taking 3rd character and 1,2nd are already taken, then we can take it only if 1st char is same as current
        if(choice==3 && prevPrevChar==s.charAt(index)){
            String temp = ""+prevPrevChar+prevChar+s.charAt(index);
            if(hashSet.contains(temp)){return faith1;}
            System.out.println(prevPrevChar+" "+prevChar+" "+s.charAt(index));
            hashSet.add(temp);
            return 1+faith1;
        }
        else if(choice==3 && prevPrevChar!=s.charAt(index)){return faith1;}
        // if we are taking 2nd char, then we can take any character
        else if(choice==2) faith2 = func2(index+1,s,s.charAt(index),prevChar,choice+1,n,hashSet);
        // if we taking first character, we can take any character
        else if(choice==1) faith2 = func2(index+1,s,s.charAt(index),prevChar,choice+1,n,hashSet);

        return faith2+faith1;
    }

    // better waw is the below method

    // first and third character has to be same and second character koi bhi ho skta hai

    // har character ka first and last index store krlo

    // ab agar first and last dono exists krte hai toh unhe first and third character bana skte hai respectively

    // we have to choose the 2nd character toh first and last index ke beech jitne bhi unique characters aa rhe hai unhe
    // and mai add krdo simple
    public int countPalindromicSubsequence(String s) {
        int n = s.length();
        Set<Character> uniqueLetters = new HashSet<>();

        for (char ch : s.toCharArray()) {
            uniqueLetters.add(ch);
        }

        int result = 0;

        for (char letter : uniqueLetters) {

            int firstIdx = -1;
            int lastIdx = -1;

            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == letter) {

                    if (firstIdx == -1) {
                        firstIdx = i;
                    }

                    lastIdx = i;

                }
            }

            Set<Character> set = new HashSet<>();
            for (int middle = firstIdx + 1; middle <= lastIdx - 1; middle++) {
                set.add(s.charAt(middle));
            }

            result += set.size();

        }

        return result;
    }

}
