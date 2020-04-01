import java.util.ArrayList;

// Solution 1
// 思路：递归处理，例如，3-bit的gray code可以在2-bit的gray code的基础上得到
// 00,01,11,10 -> (000,001,011,010) (110,111,101,100)
// 3-bit的gray code的第一组的低2位与2-bit的相同，第一组的最高位为0，第二组的最高位为1
// 并且第二组的低2位与第一组的低2位对称

class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<Integer>();
        result.add(0);
        for(int i = 0; i < n; i++) {
            int size = result.size();
            for(int k = size - 1; k >= 0; k--) {
                result.add(result.get(k) | 1 << i);
            }
        }
        return result;
    }
}