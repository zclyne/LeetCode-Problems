// 思路：注意匹配的括号串的从0开始的子串的性质：左括号数量必然>=右括号数量
// 采用递归方法，用参数numLeft和numRight分别记录当前已构成的括号串中左括号的数量和右括号的数量
// 当numLeft < n时，说明还可以加入新的左括号，因此在当前已构成的curString尾部加上左括号后递归
// 当numLeft > numRight时，说明可以加入新的右括号，在curString尾部加上右括号后递归
// 递归终止条件是numLeft == numRight == n，此时的curString是满足条件的匹配括号串

import java.util.ArrayList;
import java.util.List;
class Solution {

    ArrayList<String> res = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        if (n == 0) {
            return res;
        }
        DFS(n, 0, 0, "");
        return res;
    }

    public void DFS(int n, int numLeft, int numRight, String curString) {
        if (numLeft == n && numRight == n) { // finish
            res.add(curString);
            return;
        }
        if (numLeft < n) { // can add left parenthesis
            DFS(n, numLeft + 1, numRight, curString + "(");
        }
        if (numLeft > numRight) { // can add right parenthesis
            DFS(n, numLeft, numRight + 1, curString + ")");
        }
    }
}