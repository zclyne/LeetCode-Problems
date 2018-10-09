import java.util.Stack;
class Solution {
    public String decodeString(String s) {
        String res = "";
        Stack<Integer> nums = new Stack<>();
        Stack<Character> chars = new Stack<>();
        int tmpNum = 0;
        for (int i = 0; i < s.length(); i++)
        {
            char curChar = s.charAt(i);
            if (curChar >= '0' && curChar <= '9') tmpNum = tmpNum * 10 + (curChar - '0');
            else if (curChar >= 'a' && curChar <= 'z' || curChar >= 'A' && curChar <= 'Z')
            {
                if (nums.isEmpty()) res += curChar;
                else chars.push(curChar);
            }
            else if (curChar == '[')
            {
                nums.push(tmpNum);
                tmpNum = 0;
                chars.push(curChar);
            }
            else if (curChar == ']')
            {
                String tmpString = "";
                char topChar = chars.pop();
                while (topChar != '[')
                {
                    tmpString = topChar + tmpString;
                    topChar = chars.pop();
                }
                int countNum = nums.pop();
                String tmpRes = "";
                for (int j = 0; j < countNum; j++) tmpRes += tmpString;
                if (!nums.isEmpty()) for (int j = 0; j < tmpRes.length(); j++) chars.push(tmpRes.charAt(j)); 
                else res += tmpRes;
            }
        }
        return res;
    }
}

// faster solution
public class Solution {
    public String decodeString(String s) {
        String res = "";
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();
        int idx = 0;
        while (idx < s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                int count = 0;
                while (Character.isDigit(s.charAt(idx))) {
                    count = 10 * count + (s.charAt(idx) - '0');
                    idx++;
                }
                countStack.push(count);
            }
            else if (s.charAt(idx) == '[') {
                resStack.push(res);
                res = "";
                idx++;
            }
            else if (s.charAt(idx) == ']') {
                StringBuilder temp = new StringBuilder (resStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) temp.append(res);
                res = temp.toString();
                idx++;
            }
            else res += s.charAt(idx++);
        }
        return res;
    }
}