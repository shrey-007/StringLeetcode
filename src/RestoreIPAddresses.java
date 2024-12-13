import java.util.ArrayList;
import java.util.List;

/**
 * A valid IP address consists of exactly four integers separated by single dots. Each integer is between 0 and 255
 * (inclusive) and cannot have leading zeros.
 *
 * For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245", "192.168.1.312" and
 * "192.168@1.1" are invalid IP addresses.
 * Given a string s containing only digits, return all possible valid IP addresses that can be formed by inserting dots
 * into s. You are not allowed to reorder or remove any digits in s. You may return the valid IP addresses in any order.
 * */
public class RestoreIPAddresses {
    // we have to divide the given number into 4 parts, as ip address consist of 4 parts
    // so it is same as palindromic partition , ki apan ko given string ko partition krna hai such that no partition has
    // value more than 255
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        func(s,0,"",ans,s.length(),1);
        return ans;
    }

    public void func(String s,int index,String currentAns,List<String> ans,int n,int numberOfPartitions){
        if(numberOfPartitions>5){return;}
        if(index==n){
            if(numberOfPartitions==5){
                currentAns = currentAns.substring(0,currentAns.length()-1);
                ans.add(currentAns);
            }
        }
        for (int i = index; i <index+3 && i<n; i++) {
            String partitionedString = s.substring(index,i+1);
            long value = Long.parseLong(partitionedString);
            if(s.charAt(index)=='0' && partitionedString.length()>1){
                // means it is case of leading zeroes
                continue;
            }
            if(value>=0 && value<=255){
                func(s,i+1,currentAns+partitionedString+".",ans,n,numberOfPartitions+1);
            }
        }
    }

    public static void main(String[] args) {
        RestoreIPAddresses restoreIPAddresses = new RestoreIPAddresses();
        System.out.println(restoreIPAddresses.restoreIpAddresses("010010"));
    }
}
