import java.util.HashSet;

// 思路：用set存下J中出现的char，然后遍历S，查看字符是否存在在set中
// 可以用数组来优化

class Solution {
    public int numJewelsInStones(String J, String S) {
        HashSet<Character> set = new HashSet<>();
        int count = 0;
        for (int i = 0; i < J.length(); i++) {
            set.add(J.charAt(i));
        }
        for (int i = 0; i < S.length(); i++) {
            if (set.contains(S.charAt(i))) {
                count++;
            }
        }
        return count;
    }
}

class OptimizedSolution {
    public int numJewelsInStones(String J, String S) {
        boolean[] jewelry = new boolean[58];
        int count = 0;
        for (int i = 0; i < J.length(); i++) {
            jewelry[J.charAt(i) - 'A'] = true;
        }
        for (int i = 0; i < S.length(); i++) {
            if (jewelry[S.charAt(i) - 'A']) {
                count++;
            }
        }
        return count;
    }
}