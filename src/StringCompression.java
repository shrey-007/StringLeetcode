
/**
 * Given an array of characters chars, compress it using the following algorithm:
 *
 * Begin with an empty string s. For each group of consecutive repeating characters in chars:
 *
 * If the group's length is 1, append the character to s.
 * Otherwise, append the character followed by the group's length.
 * The compressed string s should not be returned separately, but instead, be stored in the input character array chars.
 * Note that group lengths that are 10 or longer will be split into multiple characters in chars.
 *
 * After you are done modifying the input array, return the new length of the array.
 *
 * You must write an algorithm that uses only constant extra space.
 * */
public class StringCompression {
    // this is using a constant space (string s)
    public int compress(char[] chars) {
        int start = 0;
        int count = 1;
        int end = 1;
        int n = chars.length;
        String s = "";

        while(end<n){
            char startChar = chars[start];
            char endChar = chars[end];

            // start and end char r same then increase the count
            if(startChar==endChar){count++;end++;}
            // if no then append the start char along with its count if it is more than 1
            else{
                s = s + startChar;
                if(count!=1){
                    // here the main problem of the question is itself solved, if count=12 ho gya toh s=s+count krne pr
                    // 12 add hoga and later jab string ko convert kroge array mai toh usme bhi 12 hi jaaega.
                    // but if u are not allowed to use this s string then , it will be very difficult
                    s = s + count;
                    count=1;
                }
                start = end;
                end++;
            }
        }

        // last character ke liye bhi krna padega
        s = s + chars[start];
        if(count!=1){
            s = s + count;
            count=1;
        }

        // convert string to array
        int n1 = s.length();
        for(int i=0;i<n1;i++){
            chars[i] = s.charAt(i);
        }

        return n1;
    }


    // in constant space
    // same as previous, no big change, but this code don't work when count>9, coz apan ko 123(int) ko {'1','2','3'} mai
    // convert krna padega, but ye code 123 ascii value ke respective character ko daal rha hai.
    public int compress2(char[] chars) {
        int start = 0;
        int count = 1;
        int end = 1;
        int n = chars.length;

        while(end<n){
            char startChar = chars[start];
            char endChar = chars[end];

            if(startChar==endChar){count++;end++;}
            else{
                if(count>1){
                    start++;
                    chars[start] = (char)('0'+count);
                    start = end;
                    end++;
                    count=1;
                }
                else if(count==1){
                    start = end;
                    end++;
                }
            }
        }

        start++;
        if(count>1){
            chars[start] = (char)('0'+count);
        }

        return Math.min(start+1,n);
    }

    // the main point of this quesiton is to convert 123(int) to {'1','2','3'} (character array).
    // toh pehle int ko string mai convert kro, either using inbuilt function , or can create your own function "123"
    // then us string pr iterate kro, character by character toh get digits ad character
    public int compress3(char[] chars) {
        int start = 0;
        int count = 1;
        int end = 1;
        int n = chars.length;
        int index=0;

        while(end<n){
            char startChar = chars[start];
            char endChar = chars[end];

            if(startChar==endChar){count++;end++;}
            else{
                chars[index]=startChar;
                index++;
                if(count>1){
                    String countStr = String.valueOf(count);
                    for (char c : countStr.toCharArray()) {
                        chars[index] = c;          // jojnoj
                        index++;
                    }
                    start = end;
                    end++;
                    count=1;
                }
                else if(count==1){
                    start = end;
                    end++;
                }
            }
        }


        chars[index]=chars[start];
        index++;
        if(count>1){
            String countStr = String.valueOf(count);
            for (char c : countStr.toCharArray()) {
                chars[index] = c;
                index++;
            }

        }

        return Math.min(start+1,n);
    }

    
}
