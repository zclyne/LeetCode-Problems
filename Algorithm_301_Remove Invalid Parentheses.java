import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

// 方法1：回溯法
// 用hashset possibleResults存储所有可能的结果，然后遍历一遍，找到最大的长度maxLen
// 再遍历第二遍，把长度等于maxLen的结果都加入到list result中
// 在回溯过程中，用变量count记录当前的左括号(的数量，curStr记录当前已经选择的所有字符
// 当把s遍历完毕时，如果count == 0，说明左右括号成功匹配，把当前的curStr添加到possibleResults中
// 1. 当前字符为(，有两种情况：
// 1.1. 将当前字符加入结果中，backtracking(s, pos + 1, count + 1, curStr + c)
// 1.2. 不将当前字符加入结果，backtracking(s, pos + 1, count, curStr)
// 2. 当前字符为)，有两种情况：
// 2.1. count > 0，可以选择保留当前字符，也可以选择不保留。若保留，则backtracking(s, pos + 1, count - 1, curStr + c)
// 2.2 count == 0，必须删除当前字符，backtracking(s, pos + 1, count, curStr)
// 3. 当前字符既不是(，也不是)，保留。backtracking(s, pos + 1, count, curStr + c)
// 这个方法速度较慢

class Solution {

    private Set<String> possibleResults; // using set to avoid duplicates

    public List<String> removeInvalidParentheses(String s) {
        possibleResults = new HashSet<>();
        backtracking(s, 0, 0, "");
        List<String> result = new ArrayList<>();
        int maxLen = 0;
        for (String str : possibleResults) {
            maxLen = Math.max(maxLen, str.length());
        }
        for (String str : possibleResults) {
            if (str.length() == maxLen) {
                result.add(str);
            }
        }
        return result;
    }

    private void backtracking(String s, int pos, int count, String curStr) {
        if (pos == s.length()) {
            if (count == 0) {
                possibleResults.add(curStr);
            }
            return;
        }

        char c = s.charAt(pos);
        if (c == '(') {
            backtracking(s, pos + 1, count, curStr); // remove c
            backtracking(s, pos + 1, count + 1, curStr + c); // not remove c
        } else if (c == ')') {
            if (count > 0) { // it's ok not to remove c
                backtracking(s, pos + 1, count - 1, curStr + c); // not remove c
            }
            backtracking(s, pos + 1, count, curStr); // remove c
        } else {
            backtracking(s, pos + 1, count, curStr + c);
        }
    }
}

// 其他解法：https://leetcode-cn.com/problems/remove-invalid-parentheses/solution/shan-chu-wu-xiao-de-gua-hao-by-leetcode/

// 方法2：更好的回溯法，DFS
// 先遍历一次，计算出多余的左右括号数，记为leftRemove和rightRemove
// 则这两个值就是左右括号各自所应该删除的最少的数量

class Solution {

    private int len;
    private char[] charArray;
    private Set<String> validExpressions = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {
        this.len = s.length();
        this.charArray = s.toCharArray();

        // 第 1 步：遍历一次，计算多余的左右括号
        int leftRemove = 0;
        int rightRemove = 0;
        for (int i = 0; i < len; i++) {
            if (charArray[i] == '(') {
                leftRemove++;
            } else if (charArray[i] == ')') {
                // 遇到右括号的时候，须要根据已经存在的左括号数量决定
                if (leftRemove == 0) {
                    rightRemove++;
                }
                if (leftRemove > 0) {
                    // 关键：一个右括号出现可以抵销之前遇到的左括号
                    leftRemove--;
                }
            }
        }

        // 第 2 步：回溯算法，尝试每一种可能的删除操作
        StringBuilder path = new StringBuilder();
        dfs(0, 0, 0, leftRemove, rightRemove, path);
        return new ArrayList<>(this.validExpressions);
    }

    /**
     * @param index       当前遍历到的下标
     * @param leftCount   已经遍历到的左括号的个数
     * @param rightCount  已经遍历到的右括号的个数
     * @param leftRemove  最少应该删除的左括号的个数
     * @param rightRemove 最少应该删除的右括号的个数
     * @param path        一个可能的结果
     */
    private void dfs(int index, int leftCount, int rightCount, int leftRemove, int rightRemove, StringBuilder path) {
        if (index == len) {
            if (leftRemove == 0 && rightRemove == 0) {
                validExpressions.add(path.toString());
            }
            return;
        }

        char character = charArray[index];
        // 可能的操作 1：删除当前遍历到的字符
        if (character == '(' && leftRemove > 0) {
            // 由于 leftRemove > 0，并且当前遇到的是左括号，因此可以尝试删除当前遇到的左括号
            dfs(index + 1, leftCount, rightCount, leftRemove - 1, rightRemove, path);
        }
        if (character == ')' && rightRemove > 0) {
            // 由于 rightRemove > 0，并且当前遇到的是右括号，因此可以尝试删除当前遇到的右括号
            dfs(index + 1, leftCount, rightCount, leftRemove, rightRemove - 1, path);
        }

        // 可能的操作 2：保留当前遍历到的字符
        path.append(character);
        if (character != '(' && character != ')') {
            // 如果不是括号，继续深度优先遍历
            dfs(index + 1, leftCount, rightCount, leftRemove, rightRemove, path);
        } else if (character == '(') {
            // 考虑左括号
            dfs(index + 1, leftCount + 1, rightCount, leftRemove, rightRemove, path);
        } else if (rightCount < leftCount) {
            // 考虑右括号
            dfs(index + 1, leftCount, rightCount + 1, leftRemove, rightRemove, path);
        }
        path.deleteCharAt(path.length() - 1);
    }
}

// 方法3：BFS

class Solution {

    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null) {
            return res;
        }

        // 广度优先遍历须要的队列和防止重复遍历的哈希表 visited 
        Set<String> visited = new HashSet<>();
        visited.add(s);
        Queue<String> queue = new LinkedList<>();
        queue.add(s);

        // 找到目标值以后推出循环
        boolean found = false;
        while (!queue.isEmpty()) {
            // 最优解一定在同一层
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String front = queue.poll();
                if (isValid(front)) {
                    res.add(front);
                    found = true;
                }

                int currentWordLen = front.length();
                char[] charArray = front.toCharArray();
                for (int j = 0; j < currentWordLen; j++) {
                    if (front.charAt(j) != '(' && front.charAt(j) != ')') {
                        continue;
                    }

                    // 注意 new String() 方法的 API，第 1 个参数是字符数组，第 2 个参数是字符数组的起始下标，第 3 个参数是截取的字符的长度
                    String next = new String(charArray, 0, j) + new String(charArray, j + 1, currentWordLen - j - 1);
                    if (!visited.contains(next)) {
                        queue.offer(next);
                        visited.add(next);
                    }
                }
            }

            // 这一层找到以后，退出外层循环，返回结果
            if (found) {
                break;
            }
        }
        return res;
    }

    public boolean isValid(String s) {
        char[] charArray = s.toCharArray();
        int count = 0;
        for (char c : charArray) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
            }
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }
}