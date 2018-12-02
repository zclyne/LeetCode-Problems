// 思路：按照pair[1]升序排序，从排好序的pairs中不断选取新的pair满足新pair[0] > 前一个pair[1]，并将len++即可

import java.util.Comparator;

class Solution {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] pair1, int[] pair2) {
                return pair1[1] - pair2[1];
            }
        });
        int lastNum = pairs[0][1], len = 1;
        for (int i = 1; i < pairs.length; i++) {
            if (pairs[i][0] > lastNum) {
                lastNum = pairs[i][1];
                len++;
            }
        }
        return len;
    }
}