public class CountAndSay {
    public String countAndSay(int n) {
        if(n==1){ return "1"; }
        return rle(countAndSay(n-1));
    }
    public String rle(String s){
        String ans = "";
        char lastChar = s.charAt(0);
        int n = s.length();
        int count=1;

        for (int currIndex = 1; currIndex <n ; currIndex++) {
            char currChar = s.charAt(currIndex);

            if(currChar==lastChar){
                count++;
            }
            else{
                ans = ans + count;
                count = 1;
                ans = ans+lastChar;
                lastChar = currChar;
            }
        }

        ans = ans+count;
        ans = ans+lastChar;

        return ans;
    }
    
}
