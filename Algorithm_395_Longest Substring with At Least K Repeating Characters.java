class Solution {
    public int longestSubstring(String s, int k) {
        if (k == 1 || s.length() == 0) return s.length();

        int maxLen = 0, start = 0, numChars = 0, numsSatisfied = 0, end = 0, lastInvalidIndex = -1;
        int[] totalNums = new int[26];
        int[] curNums = new int[26];
        for (int i = 0; i < 26; i++)
        {
            totalNums[i] = 0;
            curNums[i] = 0;
        }
        for (int i = 0; i < s.length(); i++) totalNums[s.charAt(i) - 'a']++;
        for (int i = 0; i < s.length(); i++)
        {
            char curChar = s.charAt(i);
            if (totalNums[curChar - 'a'] < k) // the total time of this char appearing in the string is less than k, so the result substring will never contain this char
            {
                int tmpMaxLen = longestSubstring(s.substring(lastInvalidIndex + 1, i), k); // check the last subString
                if (tmpMaxLen > maxLen) maxLen = tmpMaxLen;
                lastInvalidIndex = i;
                // start from the new subString
                numChars = 0;
                numsSatisfied = 0;
                start = end = i + 1;
                for (int j = 0; j < 26; j++) curNums[j] = 0;
                continue; // just skip this char
            }
            if (curNums[curChar - 'a'] == 0) numChars++;// a new char that has time >= k
            curNums[curChar - 'a']++; // the time of curChar appearing in the current subString
            if (curNums[curChar - 'a'] == k) numsSatisfied++;
            if (numsSatisfied == numChars) // all chars in the current substring satisfies that time >= k
                if (end - start + 1 > maxLen) maxLen = end - start + 1;
            end++;
        }
        return maxLen;
    }
}

// better recursive solution
class Solution {
    public int longestSubstring(String s, int k) {
    return helper(s.toCharArray(), 0, s.length(), k);
}
    public int helper(char[] chs, int left, int right, int k) {
        if(right - left < k) return 0;
        int[] count = new int[26];
        for(int i = left; i < right; i++)
            count[chs[i]-'a']++;
        for(int i = left; i < right; i++) {
            if(count[chs[i]-'a'] < k) {
                int j = i + 1;
                while(j < right && count[chs[j]-'a'] < k) j++;
                return Math.max(helper(chs, left, i, k), helper(chs, j, right, k));
            }
        }
        return right - left;
    }
}// time O(n log n)根据的是Master's 定理。 space O(n)因为栈

// iterative solution
public class Solution {
    public int longestSubstring(String s, int k) {
        char[] str = s.toCharArray();
        int[] counts = new int[26];
        int h, i, j, idx, max = 0, unique, noLessThanK;
        
        for (h = 1; h <= 26; h++) {
            Arrays.fill(counts, 0);
            i = 0; 
            j = 0;
            unique = 0;
            noLessThanK = 0;
            while (j < str.length) {
                if (unique <= h) {
                    idx = str[j] - 'a';
                    if (counts[idx] == 0)
                        unique++;
                    counts[idx]++;
                    if (counts[idx] == k)
                        noLessThanK++;
                    j++;
                }
                else {
                    idx = str[i] - 'a';
                    if (counts[idx] == k)
                        noLessThanK--;
                    counts[idx]--;
                    if (counts[idx] == 0)
                        unique--;
                    i++;
                }
                if (unique == h && unique == noLessThanK)
                    max = Math.max(j - i, max);
            }
        }
        
        return max;
    }
}