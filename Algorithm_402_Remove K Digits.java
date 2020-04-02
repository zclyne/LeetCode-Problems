// my solution, very slow
class Solution {
    public String removeKdigits(String num, int k) {
        int curLen = 0, len = num.length();
        char[] resChars = new char[len - k];
        for (int i = 0; i < len; i++)
        {
            char curChar = num.charAt(i);
            int j = 0;
            for (j = 0; j < curLen; j++)
            {
                if (curChar < resChars[j] && i <= k + j) // i <= k + j ensures that the remaining chars can form a valid result
                {
                    resChars[j] = curChar;
                    curLen = j + 1;
                    break;
                }
            }
            if (curLen < len - k && j == curLen) resChars[curLen++] = curChar;
        }
        for (int i = 0; i < curLen; i++) System.out.print(resChars[i]);
        int start = 0;
        String res = "";
        while (start < curLen && resChars[start] == '0') start++;
        for (int i = start; i < curLen; i++) res += resChars[i];
        return res.length() == 0 ? "0" : res;
    }
}

// solution using stack, beats 59.81%
// 思路：https://leetcode-cn.com/problems/remove-k-digits/solution/yi-diao-kwei-shu-zi-by-leetcode/#comment

public class Solution {
    public String removeKdigits(String num, int k) {
        int len = num.length();
        //corner case
        if(k==len)        
            return "0";
            
        Stack<Character> stack = new Stack<>();
        int i =0;
        while(i<num.length()){
            //whenever meet a digit which is less than the previous digit, discard the previous one
            while(k>0 && !stack.isEmpty() && stack.peek()>num.charAt(i)){
                stack.pop();
                k--; // k表示额度，每当从栈顶pop一个元素时，表示不使用该元素，则额度减1.当额度减到0时，之后的字符串必须全部选取，不能删除
            }
            stack.push(num.charAt(i));
            i++;
        }
        
        // corner case like "1111"
        while(k>0){ // 额度尚未用完
            stack.pop();
            k--;            
        }
        
        //construct the number from the stack
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty())
            sb.append(stack.pop());
        
        //remove all the 0 at the head
        while(sb.length()>1 && sb.charAt(sb.length() - 1)=='0')
            sb.deleteCharAt(sb.length() - 1);
        return sb.reverse().toString();
    }
}

// greedy solution
public class Solution {
    public String removeKdigits(String num, int k) {
        int digits = num.length() - k;
        char[] stk = new char[num.length()];
        int top = 0;
        // k keeps track of how many characters we can remove
        // if the previous character in stk is larger than the current one
        // then removing it will get a smaller number
        // but we can only do so when k is larger than 0
        for (int i = 0; i < num.length(); ++i) {
            char c = num.charAt(i);
            while (top > 0 && stk[top-1] > c && k > 0) {
                top -= 1;
                k -= 1;
            }
            stk[top++] = c;
        }
        // find the index of first non-zero digit
        int idx = 0;
        while (idx < digits && stk[idx] == '0') idx++;
        return idx == digits? "0": new String(stk, idx, digits - idx);
    }
}