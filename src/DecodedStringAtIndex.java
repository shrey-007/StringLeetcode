/**
 * You are given an encoded string s. To decode the string to a tape, the encoded string is read one character at a time
 * and the following steps are taken:
 *
 * If the character read is a letter, that letter is written onto the tape.
 * If the character read is a digit d, the entire current tape is repeatedly written d - 1 more times in total.
 * Given an integer k, return the kth letter (1-indexed) in the decoded string.
 * */
public class DecodedStringAtIndex{
    public String decodeAtIndex2(String s, int k) {
        String  ans = "";
        int n = s.length();

        for(int i=0;i<n;i++){
            char curr = s.charAt(i);
            if(Character.isDigit(curr)){
                int d = Character.getNumericValue(curr);
                String currentTape = ans;
                for(int j=1;j<=d-1;j++){
                    ans += currentTape;
                }
            }
            else{
                ans = ans + curr;
            }
        }
        System.out.println(ans);
        System.out.println(ans.length());

        return ans.charAt(k-1)+"";
    }

    // giving memory limit exceeded
    // another way
    public String decodeAtIndex(String s, int k) {
        int currentLength = 0;
        int n = s.length();
        int lastDigitIndex = -1;
        int lastLengthAchieved = 0;

        for(int i=0;i<n;i++){
            char curr = s.charAt(i);
            if(Character.isDigit(curr)){
                int d = Character.getNumericValue(curr);
                currentLength = currentLength*d;
                if(currentLength<k){
                    lastDigitIndex = i;
                    lastLengthAchieved = currentLength;
                }
            }
            else{
                currentLength++;
                if(currentLength==k){return s.charAt(i)+"";}
            }
        }

        return "";
    }
    // this method will not work for s="ha22" and k=5

    
}
