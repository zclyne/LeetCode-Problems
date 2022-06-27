// 用一个长度为26的数组记录s中各字符第一次出现的下标，数组元素初始值为-1
// 如果一个字符出现了超过1次，就将数组中对应位置上的值设为-2
// 最后再遍历一遍该数组，找到valid下标的最小值并返回

class Solution {
    public int firstUniqChar(String s) {
        int[] recordIndex = new int[26];
        Arrays.fill(recordIndex, -1);

        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            if (recordIndex[idx] == -1) { // not encountered before
                recordIndex[idx] = i;
            } else { // have encountered this char before
                recordIndex[idx] = -2;
            }
        }

        int minIdx = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            if (recordIndex[i] >= 0) {
                minIdx = Math.min(minIdx, recordIndex[i]);
            }
        }

        return minIdx == Integer.MAX_VALUE ? -1 : minIdx;
    }
}