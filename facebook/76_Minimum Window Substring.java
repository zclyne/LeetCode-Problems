class Solution {
    public String minWindow(String s, String t) {
        int charCount = 0;
        int[] tCharsCount = new int[52];
        for (char c : t.toCharArray()) {
            if (tCharsCount[charToIndex(c)] == 0) { // encounter a new char in t
                charCount++;
            }
            tCharsCount[charToIndex(c)]++;
        }

        int[] sCharsCount = new int[52];
        String result = "";
        int left = 0, right = -1;
        while (true) {
            if (charCount > 0) {
                if (right == s.length() - 1) { // can't extend any more
                    break;
                } else { // extend the right pointer
                    right++;
                    char c = s.charAt(right);
                    int idx = charToIndex(c);
                    sCharsCount[idx]++;
                    if (sCharsCount[idx] == tCharsCount[idx]) { // the number of c matches
                        charCount--;
                    }
                }
            } else {
                if ("".equals(result) || result.length() > right - left + 1) {
                    result = s.substring(left, right + 1);
                }
                // shrink left
                char c = s.charAt(left);
                left++;
                int idx = charToIndex(c);
                sCharsCount[idx]--;
                if (sCharsCount[idx] < tCharsCount[idx]) {
                    charCount++;
                }
            }
        }
        return result;
    }

    private int charToIndex(char c) {
        if (Character.isLowerCase(c)) {
            return c - 'a';
        } else {
            return c - 'A' + 26;
        }
    }
}