// 先split
// 不能直接返回split后的数组的长度，因为题目要求如果segment是空字符串""，则不算

class Solution {
    public int countSegments(String s) {
        int count = 0;
        String[] segments = s.split(" ");
        for (String seg : segments) {
            if (!"".equals(seg)) {
                count++;
            }
        }
        return count;
    }
}