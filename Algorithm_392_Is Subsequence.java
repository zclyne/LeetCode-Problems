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