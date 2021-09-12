class Solution {
    private int result = 1;

    public int maxProduct(String s) {
        dfs(s, 0, "", "");
        return result;
    }

    private void dfs(String s, int cur, String a, String b) {
        if (isPalindrome(a) && isPalindrome(b)) {
            result = Math.max(result, a.length() * b.length());
        }

        if (cur == s.length()) {
            return;
        }
        
        // select s[cur] into a
        dfs(s, cur + 1, a + s.charAt(cur), b);
        // select s[cur] into b
        dfs(s, cur + 1, a, b + s.charAt(cur));
        // not select s[cur]
        dfs(s, cur + 1, a, b);
    }

    private boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}