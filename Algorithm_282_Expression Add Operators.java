import java.util.ArrayList;
import java.util.List;

// 方法：回溯法
// 从左到右遍历num，分别添加+、-、*符号，并计算表达式结果，直到num被遍历完
// 注意由于乘法的优先级更高，所以需要特殊处理，具体方法是在backtracking方法上
// 加上一个参数lastOperand，记录表达式中的上一个操作数，这个操作数可能为正，也可能为负
// 具体取决于这个操作数本身的上一个符号是+还是-
// 还要注意如果curNum == 0时，只允许for循环的第一次循环，因为后续的循环相当于curNum是一个首位为0的多位整数，不合法

class Solution {

    private List<String> result;

    public List<String> addOperators(String num, int target) {
        result = new ArrayList<>();
        backtracking(num, 0L, target, 0, 0L, "");
        return result;
    }

    // notice that the types of val and lastOperand are long, not int
    // otherwise overflow might occur
    private void backtracking(String num, long val, int target, int pos, long lastOperand, String exp) {
        if (pos == num.length()) {
            if (val == target) {
                result.add(exp);
            }
            return;
        }

        // type of curNum should also be long rather than int
        long curNum = 0;
        for (int i = pos; i < num.length(); i++) {
            curNum = curNum * 10 + (num.charAt(i) - '0');
            if (pos == 0) { // the 1st number, don't add operator
                backtracking(num, curNum, target, i + 1, curNum, exp + curNum);
            } else {
                // plus
                backtracking(num, val + curNum, target, i + 1, curNum, exp + "+" + curNum);
                // minus
                backtracking(num, val - curNum, target, i + 1, -curNum, exp + "-" + curNum);
                // multiply, which has a higher priority
                backtracking(num, val - lastOperand + lastOperand * curNum, target, i + 1, lastOperand * curNum, exp + "*" + curNum);
            }
            if (curNum == 0) { // number whose 1st digit is 0 is not allowed
                return;
            }
        }
    }
}