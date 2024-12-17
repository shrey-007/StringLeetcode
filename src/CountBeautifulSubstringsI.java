import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

/**
 * You are given a string s and a positive integer k.
 *
 * Let vowels and consonants be the number of vowels and consonants in a string.
 *
 * A string is beautiful if:
 *
 * vowels == consonants.
 * (vowels * consonants) % k == 0, in other terms the multiplication of vowels and consonants is divisible by k.
 * Return the number of non-empty beautiful substrings in the given string s.
 *
 * A substring is a contiguous sequence of characters in a string.
 *
 * Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
 *
 * Consonant letters in English are every letter except vowels.
 * */
public class CountBeautifulSubstringsI {

    // first approach is brute force and since 1 <= s.length <= 1000 toh ye O(N^2) solution bhi chal jaaega
    public int beautifulSubstrings2(String s, int k) {
        int n = s.length();
        int ans = 0;

        for(int i=0;i<n;i++){
            int vowels = 0;
            int consonants = 0;
            for(int j=i;j<n;j++){
                char curr = s.charAt(j);
                if(curr=='a' || curr=='e' || curr=='i' || curr=='o' || curr=='u') vowels++;
                else consonants++;

                if(vowels==consonants && (vowels*consonants)%k==0) ans++;
            }
        }

        return ans;
    }

    // if we want to optimize it, since it is a substring question either sliding window or prefix sum is going to be used
    /**
     * Approach is same as 2 sum. Reverse engineering.
     * suppose string = "baeyh", lets count number of vowel(v), number of consonant (c), v-c till each index
     * index -     0      1     2     3      4
     * string -    b      a     e     y      h
     * v -         0      1     2     2      2
     * c -         1      1     1     2      3
     * v-c -       -1     0     1     0      -1
     *
     * now index 4 and index 0 have same (v-c), so the substring [0+1,4] means [1,4] have vowels=consonants
     * similarly 1,3 have same v-c toh [2,3] substring mai vowels=consonants hai
     *
     * also [0,3] and [0,1] mai bhi vowels=consonants but voh dono toh nhi pata pade iske hisaab se, kiuki apan ne initial
     * condition nhi daali.
     * Initial condition - assume index -1 jisme v-c=0 hai
     *
     * Toh ab index -1,1 have same v-c=0 toh [0,1] have vowels=consonants
     * similarly index -1,3 have same v-c=0 toh [0,3] have vowels=consonants
     *
     * toh apan ko 0 to n iterate krna hai har i ke liye v,c,v-c calculate krna hai and check krna hai ki isse pehle konse
     * ese index hai jinka v-c i ke v-c ke equal hai, voh apan hashmap ke help se pata kr lenge. Voh jo indexes ki list hogi
     * unse i tak ki substring mai vowels=consonants vaala constraint fullfill hoga. But we need to check modulo vaala constraint also
     *
     * suppose apan index pr hai jiska v-c x hai. apan ne hashmap se indexes p,q,r nikaale jinka v-c bhi x hai
     * means substring(p+1,i+1),substring(q+1,i+1),substring(r+1,i+1) have vowels=consonants ye apan ne pata kr liya.
     * Ab to check 2nd constraint we need to find number of vowels in the substrings substring(p+1,i+1),substring(q+1,i+1),substring(r+1,i+1)
     * Ye krne ke liye prefix vowel use kra, number of vowels from p to i = prefixVowel(i)-prefixVowel(p)
     *
     * Why this works?
     * We need to find number of substrings which have vowel=consonant, let vowel=1, consonant=-1, so question reduces to
     * find number of subarrays with sum 0. This is already solved, That's why this approach works
     *
     * You know the best part of this question?
     * I solved it myself, using both approach, maine kahi hint, youtube discuss section kahi nhi dekha khud se kra, means
     * i am getting better at leedcode.
     * However this below soltion also gives TLE, but this same aproach works for c++ without TLE, so don't worry
     * * */

    public long beautifulSubstrings(String s, int k) {
        int n = s.length();
        long ans = 0;

        // store list of indices jinka vowels-consonants difference ho
        // (vowels-consonants)=> list of indices jinka count of vowels-count of consonats = vowels-consonants ho
        HashMap<Integer, ArrayList<Integer>> hashMap = new HashMap<>();
        // initially -1 index pr difference 0 kr diya
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(-1);
        hashMap.put(0,temp);

        // to store prefixSum of vowels
        ArrayList<Integer> prefixVowels = new ArrayList<>();
        prefixVowels.add(0);

        // to count number of vowel and cons. in a single iteration
        int vowels = 0;
        int consonants = 0;

        for (int i = 0; i < n; i++) {
            // if the current char is vowel so increment vowel count else increment consonant count
            char curr = s.charAt(i);
            if(curr=='a' || curr=='e' || curr=='i' || curr=='o' || curr=='u') vowels++;
            else consonants++;
            // add current count in the prefix of vowel
            prefixVowels.add(vowels);

            // find whether if there exists any other index with same vowel-consonat difference
            ArrayList<Integer> list = hashMap.getOrDefault(vowels-consonants,new ArrayList<>());

            // iterate over all those indexes
            for (int index: list){
                // find the number of vowels in those index by prefixVowels.get(index+1)
                // find the numner of vowels from index to i
                int numberOfVowels = vowels-prefixVowels.get(index+1);
                if(((numberOfVowels%k)*(numberOfVowels%k))%k==0) ans++;
                // if above condition is true then the substring index to i has same number of vowels and consonants
                // anf there multipliction mod k is 0, so increase answer.
            }

            // since current index also has number of vowel-number of consonants = vowel-consonant so update current
            // index
            list.add(i);
            hashMap.put(vowels-consonants,list);
        }

        return ans;
    }

    // This also gave TLE, sed lyfe



}
