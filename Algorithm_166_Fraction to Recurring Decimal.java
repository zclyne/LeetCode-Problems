import java.util.HashMap;

// 思路：首先判断分子是否为0，然后根据分子分母与0的关系判断是否要添加负号
// 把分子和分母都转换成其绝对值。为防止溢出，要使用long来存储转换的结果
// 先做一次除法得到整数部分，并添加到res中，判断剩下的num是否为0.若为0则整除，直接返回；不为0则添加一个小数点
// 对剩余的num建立一个map，键为num，值为此时的res长度
// 每次把num * 10后除以div，并把num对div取余。若得到的num已经存在在map中，说明产生了循环小数
// 通过查map得到'('的插入位置，并在res末尾添加右括号，并退出循环
// 若num达到0，则除完了，退出循环
// 返回res.toString()

class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        StringBuilder res = new StringBuilder();
        res.append((numerator ^ denominator) < 0 ? "-" : "");
        long num = Math.abs((long) numerator);
        long div = Math.abs((long) denominator);
        res.append(num / div);
        num %= div;
        if (num == 0) { // no decimal part
            return res.toString();
        }

        // handle decimal part
        res.append(".");
        HashMap<Long, Integer> map = new HashMap<>();
        map.put(num, res.length());
        while (num != 0) {
            num *= 10;
            res.append(num / div);
            num %= div;
            if (map.containsKey(num)) { // repeat
                res.insert(map.get(num), "(");
                res.append(")");
                break;
            } else {
                map.put(num, res.length());
            }
        }
        return res.toString();
    }
}