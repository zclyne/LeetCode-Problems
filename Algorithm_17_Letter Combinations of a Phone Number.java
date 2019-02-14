// 思路：用队列存储当前所有可能的String，当读到一个新的digit时，获取当前队列的size，并按顺序从头部取出size个String
// 这些String就是上一个digit按下后所有可能的结果。将当前digit对应的所有字符添加到每一个字符串的结尾
// 就构成了新的所有字符串结果
// 在digits遍历结束后，队列中的所有字符串就是结果

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) { // pay attention here
            return res;
        }
        String[] letterMap = new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        Queue<String> queue = new LinkedList<>();
        queue.offer("");
        for (int i = 0; i < digits.length(); i++) {
            int curSize = queue.size(); // we have to get the size before the for loop, because the size is changing during the loop
            char[] chars = letterMap[Character.getNumericValue(digits.charAt(i))].toCharArray();
            for (int count = 0; count < curSize; count++) {
                String curString = queue.poll(); // get the String at the front of queue and delete it
                for (char c : chars) { // add each char related to this digit to the end of curString, and then add it to queue
                    queue.offer(curString + c);
                }
            }
        }
        res.addAll(queue);
        return res;
    }
}