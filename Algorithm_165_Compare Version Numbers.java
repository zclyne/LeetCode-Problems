// 方法：直接从左到右遍历version1和version2，parse出每一个revision并进行比较

class Solution {
    public int compareVersion(String version1, String version2) {
        int i = 0, j = 0;
        while (i < version1.length() || j < version2.length()) {
            int revision1 = 0, revision2 = 0;
            while (i < version1.length() && Character.isDigit(version1.charAt(i))) {
                revision1 = revision1 * 10 + (version1.charAt(i) - '0');
                i++;
            }
            while (j < version2.length() && Character.isDigit(version2.charAt(j))) {
                revision2 = revision2 * 10 + (version2.charAt(j) - '0');
                j++;
            }
            if (revision1 < revision2) {
                return -1;
            } else if (revision2 < revision1) {
                return 1;
            } else {
                i++;
                j++;
            }
        }
        return 0;
    }
}