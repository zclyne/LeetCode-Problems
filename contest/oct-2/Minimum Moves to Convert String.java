class Solution {
    public int minimumMoves(String s) {
        int left = 0, right = 0;
        int result = 0;
        while (left < s.length()) {
            if (s.charAt(left) == 'O') {
                left++;
                right++;
                continue;
            }
            result++;
            while (right < s.length() && right < left + 3) {
                right++;
            }
            left = right;
        }
        return result;
    }
}