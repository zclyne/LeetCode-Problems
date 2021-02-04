// 思路：本质上是10进制转26进制
// 但特殊之处在于此处26进制的范围是1~26，而非0~25
// 所以在循环体中首先要将n--

public class Solution {
    public String convertToTitle(int n) {
        StringBuilder result = new StringBuilder();

        while(n > 0){
            n--;
            result.insert(0, (char) ('A' + n % 26));
            n /= 26;
        }

        return result.toString();
    }
}