import java.util.*;

// think reversely
// 首先找到最后一次replace的位置，对应于target中和stamp完全相同的substring
// 将这个substring用*替换，得到一个新的t
// 每次从t中找到一个合法的长度等于stamp.length()的substring，将其对应字符全部替换成*
// 并且将这个substring的起始位置存到list中
// 直到最终*的数量等于target的长度，表明target能够由多次替换stamp得到
// 将res中的元素反序后返回
// 如果在某次迭代中没有找到这样的substring，说明无法替换，直接return空数组
// https://leetcode.com/problems/stamping-the-sequence/discuss/201546/12ms-Java-Solution-Beats-100

class Solution {
    public int[] movesToStamp(String stamp, String target) {
        char[] s = stamp.toCharArray();
        char[] t = target.toCharArray();
        boolean[] replaced = new boolean[t.length];
        int countStars = 0;
        List<Integer> res = new ArrayList<>();

        while (countStars < t.length) {
            boolean doneReplace = false;
            // traverse t and look for a substring that might be
            // stamped by s
            for (int i = 0; i <= t.length - s.length; i++) {
                if (!replaced[i] && canReplace(s, t, i)) {
                    countStars = doReplace(t, s.length, i, countStars);
                    replaced[i] = true;
                    doneReplace = true;
                    res.add(i);
                }
            }
            if (!doneReplace) {
                // failed to replace any substring in t in this iteration
                return new int[0];
            }
        }

        // convert List<Integer> to int[], and reverse the order
        int[] result = new int[res.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = res.get(res.size() - i - 1);
        }

        return result;
    }

    // check whether t[idx:idx+s.length] can be stamped by s
    private boolean canReplace(char[] s, char[] t, int idx) {
        for (int i = 0; i < s.length; i++) {
            if (t[i + idx] != '*' && t[i + idx] != s[i]) { // not match
                return false;
            }
        }
        return true;
    }

    // replace t[idx:idx+stampLen] with '*'s, and return the
    // new count of '*'s
    private int doReplace(char[] t, int stampLen, int idx, int countStars) {
        for (int i = 0; i < stampLen; i++) {
            if (t[i + idx] != '*') {
                t[i + idx] = '*';
                countStars++;
            }
        }
        return countStars;
    }
}