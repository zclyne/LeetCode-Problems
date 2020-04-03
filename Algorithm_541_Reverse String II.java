// 思路：用变量shouldReverse记录是否需要反向，StringBuilder暂存字符
// 若builder.length() == k，表示已经暂存了k个字符，根据shouldReverse的值
// 来判断是否反向

class Solution {
    public String reverseStr(String s, int k) {
        if (k == 1) {
            return s;
        }
        boolean shouldReverse = true;
        StringBuilder builder = new StringBuilder();
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            builder.append(s.charAt(i));
            if (builder.length() == k) {
                result += shouldReverse ? builder.reverse().toString() : builder.toString();
                shouldReverse = !shouldReverse;
                builder = new StringBuilder();
            }
        }
        if (builder.length() > 0) { // still has some chars remain unhandled
            result += shouldReverse ? builder.reverse().toString() : builder.toString();
        }
        return result;
    }
}