class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s.length() > t.length()) return false;
        int p1 = 0, p2 = 0;
        while (p1 < s.length() && p2 < t.length())
        {
            if (s.charAt(p1) == t.charAt(p2)) p1++;
            p2++;
        }
        return p1 == s.length();
    }
}

// another solution
class Solution {
    public boolean isSubsequence(String s, String t) {
        int idx = -1;
        for(int i = 0; i < s.length(); i++)
        {
            int cur = t.indexOf(s.charAt(i), idx+1);
            if(cur == -1) return false;
            idx = cur;
        }
        return true;
    }
}

// Follow-up: If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, 
// and you want to check one by one to see if T has its subsequence.
// In this scenario, how would you change your code?
// 思路：先把t中的字符对应的下标存到map里
// 用二分查找来判断s的当前字符是否存在于t中
// 对具有很多s时很有效

class FollowUpSolution {
    public boolean isSubsequence(String s, String t) {
        if (s == null || t == null) return false;
        
        Map<Character, List<Integer>> map = new HashMap<>(); //<character, index>
        
        //preprocess t
        for (int i = 0; i < t.length(); i++) {
            char curr = t.charAt(i);
            if (!map.containsKey(curr)) {
                map.put(curr, new ArrayList<Integer>());
            }
            map.get(curr).add(i);
        }
        
        int prev = -1;  //index of previous character
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (map.get(c) == null)  {
                return false;
            } else {
                List<Integer> list = map.get(c);
                prev = binarySearch(prev, list, 0, list.size() - 1);
                if (prev == -1) {
                    return false;
                }
                prev++;
            }
        }
        
        return true;
    }
    
    private int binarySearch(int index, List<Integer> list, int start, int end) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (list.get(mid) < index) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        
        return start == list.size() ? -1 : list.get(start);
    }
}