import java.util.HashSet;

/**
 * Given an array of strings nums containing n unique binary strings each of length n, return a binary string of length
 * n that does not appear in nums. If there are multiple answers, you may return any of them.
 * */
public class FindUniqueBinaryString {
    // most brute force way is ki generate all possible binary string and in the base case check whether it is present
    // int the nums or not, if not that is the answer
    public String findDifferentBinaryString(String[] nums) {
        int n = nums.length;

        HashSet<String> hashSet = new HashSet<>();

        for (int i = 0; i < n; i++) {
            hashSet.add(nums[i]);
        }

        return func(hashSet,0,"",n);
    }

    public String func(HashSet<String> hashSet,int index,String currentString,int n){
        if(index==n){
            if (hashSet.contains(currentString)) return "";
            return currentString;
        }
        // append 0
        String faith1 = func(hashSet,index+1,currentString+"1",n);
        if(faith1!="") return faith1;
        // append 1
        String faith2 = func(hashSet,index+1,currentString+"0",n);
        return faith2;
    }

    // another approach which is very easy and intuitive does this in just O(n)
    // suppose nums={011,111,101}
    // toh first string pr jaao and first index mai dekho kya hai it is 0 toh tum 1 daalo ans mai. Reason is hum esi string
    // banana chahte hai jo first se different ho. since first mai 0 hai and humne 1 daala toh agar uske baad ke saare charaters
    // same bhi ho jaaye tab bhi string dirrefent hi hogi 011 is different from 111

    // toh abhi tak ka ans="1" hai and ye string pakka 0th string se diffeent hai since 0th string ka first char 0 hai and
    // iska 1
    // ab second string pr jaao and 1st index dekho voh 1 hai toh tum 0 laga do ans mai. ans="10" so will definetely
    // different from 1,2nd string both and so on krte jaao.
    public String findDifferentBinaryString2(String[] nums) {
        int n = nums.length;

        StringBuilder stringBuilder = new StringBuilder("");

        for (int i = 0; i < n; i++) {
            char curr = nums[i].charAt(i);
            if(curr=='0') stringBuilder.append(1);
            else stringBuilder.append(0);
        }

        return stringBuilder.toString();
    }
}
