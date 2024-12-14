///**
// * Given a string of digits s, return the number of palindromic subsequences of s having length 5. Since the answer may
// * be very large, return it modulo 109 + 7.
// *
// * Note:
// *
// * A string is palindromic if it reads the same forward and backward.
// * A subsequence is a string that can be derived from another string by deleting some or no characters without changing
// * the order of the remaining characters.
// * */
//public class CountPalindromicSubsequences {
//    // since it is subsequence problem toh recursion use ho skta hai
//    // also longest palindromic subsequence dp ka question hai jisme hum s and reverse of s ka lcs nikalte hai
//    // toh ye bhi same tarah se ho skta hai
//    // it is count problem toh base case mai 1 and baaki negative cases mai 0 return krna hai
//    public int countPalindromes(String s) {
//        String reverse = "";
//        int n = s.length();
//
//        for (int i = 0; i < n; i++) {
//            reverse = s.charAt(i) + reverse;
//        }
//
//        return func(s,reverse,0,n);
//    }
//
//    public int func(String s1,String s2,int index,int n){
//        // take this index
//        // but we can only take this if
//    }
//
//    public static void main(String[] args) {
//        CountPalindromicSubsequences countPalindromicSubsequences = new CountPalindromicSubsequences();
//        countPalindromicSubsequences.countPalindromes("shrey");
//
//    }
//}
