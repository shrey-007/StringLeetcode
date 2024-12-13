public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int start = 0;
        int end = 0;

        int n = s.length();
        int openingBrackets = 0;
        int closingBrackets = 0;
        int ans = 0;

        while (end<n){
            // update count of brackets
            char curr = s.charAt(end);
            if(curr=='('){openingBrackets++;}
            else{closingBrackets++;}

            if(openingBrackets==closingBrackets){
                // this is possible answer so update it
                ans = Math.max(ans,end-start+1);
                end++;
            }

            else if(closingBrackets>openingBrackets && start<end){
                while (closingBrackets>openingBrackets){
                    char charAtStart = s.charAt(start);
                    if(charAtStart=='('){openingBrackets--;}
                    else{closingBrackets--;}
                    start++;
                }
                if(closingBrackets==openingBrackets) ans = Math.max(ans,end-start+1);
                end++;
            }

            else if(openingBrackets>closingBrackets){
                end++;
            }
        }

        while (closingBrackets<openingBrackets){
            char charAtStart = s.charAt(start);
            if(charAtStart=='('){openingBrackets--;}
            else{closingBrackets--;}
            start++;
        }
        if(closingBrackets==openingBrackets) ans = Math.max(ans,end-start+1);

        return ans;
    }

    public static void main(String[] args) {
        LongestValidParentheses longestValidParentheses = new LongestValidParentheses();
        System.out.println(longestValidParentheses.longestValidParentheses(")()())(())(()"));
    }
}
