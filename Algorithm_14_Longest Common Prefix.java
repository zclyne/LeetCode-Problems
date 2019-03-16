import java.util.Arrays;

// better solution
// 思路：先假设strs[0]就是res，然后到其余所有str中寻找res所在的起始下标。
// 若strs[i].indexOf(res) != 0，则res一定不是strs[i]的起始字符串，此时去掉res的最后一个字符
// 即res = res.substring(0, res.length() - 1)，然后重新比较，直至等于0为止
// 最终res就是答案
// 说明：int indexOf(String str): 返回指定字符在字符串中第一次出现处的索引，如果此字符串中没有这样的字符，则返回 -1

class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String res = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(res) != 0) {
                res = res.substring(0, res.length() - 1);
            }
        }
        return res;
    }
}



// My Solution
// 思路：与better solution基本相同，把判断方式改为逐个字符判断

class MySolution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String res = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int cur = 0;
            while (cur < res.length() && cur < strs[i].length()&& res.charAt(cur) == strs[i].charAt(cur)) {
                cur++;
            }
            res = res.substring(0, cur);
        }
        return res;
    }
}


// Sort Solution
// 思路：先对数组做排序，排序后相差最大的2个字符串会分别位于数组的首部和尾部，然后找这两个字符串的common prefix

class SortSolution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
       }
        StringBuilder builder = new StringBuilder();
        Arrays.sort(strs);
        String s = strs[0], t = strs[strs.length - 1];
        for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                break;
            } else {
                builder.append(s.charAt(i));
            }
        }
        return builder.toString();
    }
}
