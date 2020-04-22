import java.util.ArrayList;
import java.util.List;

// DFS Solution
// 思路：对每一层递归，判断ip中的一段是否符合要求
// 注意ip中的一段数字不能以0开头，并且不能大于255，但是单独的0是允许的

class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> solutions = new ArrayList<String>();
        restoreIp(s, solutions, 0, "", 0);
        return solutions;
    }
    
    private void restoreIp(String ip, List<String> solutions, int idx, String restored, int count) {
        if (count == 4) {
            if (idx == ip.length()) {
                solutions.add(restored);
            }
            return;
        }
        
        for (int i = 1; i <= 3; i++) {
            if (idx + i > ip.length()) {
                break;
            }
            String s = ip.substring(idx, idx + i);
            if ((s.startsWith("0") && s.length() > 1) || Integer.parseInt(s) > 255) {
                continue;
            }
            restoreIp(ip, solutions, idx + i, restored + s + (count == 3 ? "" : "."), count + 1);
        }
    }
}

// brute-force solution

class BruteForceSolution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<String>();
        int len = s.length();
        for(int i = 1; i<4 && i<len-2; i++){
            for(int j = i+1; j<i+4 && j<len-1; j++){
                for(int k = j+1; k<j+4 && k<len; k++){
                    String s1 = s.substring(0,i), s2 = s.substring(i,j), s3 = s.substring(j,k), s4 = s.substring(k,len);
                    if(isValid(s1) && isValid(s2) && isValid(s3) && isValid(s4)){
                        res.add(s1+"."+s2+"."+s3+"."+s4);
                    }
                }
            }
        }
        return res;
    }
    public boolean isValid(String s) {
        if(s.length()>3 || s.length()==0 || (s.charAt(0)=='0' && s.length()>1) || Integer.parseInt(s)>255)
            return false;
        return true;
    }
}