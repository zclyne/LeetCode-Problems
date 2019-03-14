// 思路：把各种情况下的罗马数字存入数组中，然后由大到小遍历数组，把罗马数字添加到res的尾部

class Solution {
    public String intToRoman(int num) {
        int[] nums = new int[] {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        String[] romanChars = new String[] {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"}; // 1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000
        StringBuilder res = new StringBuilder();
        for (int i = nums.length - 1; i >= 0; i--) {
            int curNum = nums[i];
            while (num >= curNum) {
                res.append(romanChars[i]);
                num -= curNum;
            }
        }
        return res.toString();
    }
}