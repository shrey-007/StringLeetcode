import java.util.HashMap;

/**
 * Given a string s, return the number of homogenous substrings of s. Since the answer may be too large,
 * return it modulo 109 + 7.
 *
 * A string is homogenous if all the characters of the string are the same.
 *
 * A substring is a contiguous sequence of characters within a string.
 * */
public class CountNumberOfHomogenousSubstrings {
    // the most brute force approach
    public int countHomogenous2(String s) {
        int n = s.length();
        int count = 0;
        for (int i = 0; i <n; i++) {
            char lastChar = s.charAt(i);
            for (int j = i; j < n; j++) {
                char currentChar = s.charAt(j);
                if(lastChar==currentChar){
                    count++;
                }
                else{
                    break;
                }
            }
        }

        return count;
    }

    public int countHomogenous3(String s) {
        return func(s);
    }

    // since it is substring problem so i used sliding window
    // since it is a count problem, toh count vaali algorithm lagegi
    public int func(String s){
        HashMap<Character,Integer> hashMap = new HashMap<>();
        int n = s.length();
        int start=0;
        int end=0;
        int mod = 1000000007;

        int ans=0;

        while (end<n){
            // do work on end
            char charAtEnd = s.charAt(end);
            hashMap.put(charAtEnd,hashMap.getOrDefault(charAtEnd,0)+1);

            while (hashMap.size()>1){
                char charAtStart = s.charAt(start);
                hashMap.put(charAtStart,hashMap.get(charAtStart)-1);
                if(hashMap.get(charAtStart)==0){hashMap.remove(charAtStart);}
                start++;
            }

            ans=((ans)%mod+(end-start)%mod+1)%mod;   // means (end-start+1) is the count of subarrays to be added to ans
            end++;
        }

        return ans;
    }

    // Most optimal approach is-:
    // must see why and how this works
    /*
    * One and only thing you need to know to make this problem Easy is that homogenous string "aaaaaaa" of length N
    * contains N * (N + 1) / 2 homogenous substrings (sum of the arithmetic progression).
    * Then you just sum them up not forgetting about modulo.
    *  */
    public int countHomogenous(String s){
        char prev = '*';
        int lengthOfSubstring = 1;
        int ans = 0;
        int n = s.length();

        for(int i=0;i<n;i++){
            char curr  = s.charAt(i);
            if(curr!=prev){
                // means ki new string banao i+1 index se
                lengthOfSubstring = 1;
                prev = curr;
            }
            // means ki abhi prev se match kr rha hai curr, toh
            ans = (ans%1000000007 + lengthOfSubstring%1000000007)%1000000007;
            lengthOfSubstring++;
        }

        return ans;
    }

}
