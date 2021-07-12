// 方法1：将一个数字拆分成最高位high和其右边的部分last
// 例如n = 3452, high = 3, last = 452
// power表示n的量级。例如n = 3452时，power = 1000
// 先看最高位。如果最高位大于1，例如此处最高位high = 3，则[0, 3452]区间内包含了[1000, 1999]所有的数字
// 则最高位为1这个区间里，在最高位上贡献了1000个1，即power个1
// 再考虑后3位的部分。[0, 999], [1, 1999], [2, 2999]这3个区间在后3位上贡献的1的数量是相同的
// 则这三个区间在后3位上贡献的1的个数 == 3 * countDigitOne(1000)
// 最后考虑[3000, 3452]这个区间。这个区间贡献的1的个数和[0, 452]是相同的，所以再加上countDigitOne(last)即可
// 如果最高位是1，则可以将数字n分成2个部分。以n = 1452为例，可以把[0, n]分为以下2个区间：[0, 999], [1000, 1452]
// [0, 999]范围贡献的1的个数等于countDigitOne(power - 1)
// [1000, 1452]的最高位为1，这部分在最高位上贡献的1的个数就等于452 + 1 = 453，即last + 1
// 最后，[1000, 1452]在后3位上贡献的1的个数和[0, 452]是相同的，等于countDigitOne(last)

class Solution {
    public int countDigitOne(int n){
        if (n <= 0) {
            return 0;
        }
        if (n < 10) {
            return 1;
        }
        String s = String.valueOf(n);
        int last = Integer.parseInt(s.substring(1));
        int power = (int) Math.pow(10, s.length() - 1);
        int high = s.charAt(0) - '0';
        if (high == 1){ // 最高位是1，贡献的1的个数 == last
            return countDigitOne(last) + countDigitOne(power - 1) + last + 1;
        }else { // 最高位大于1，则最高位是1的那部分贡献的1的个数为power
            return power + high * countDigitOne(power - 1) + countDigitOne(last);
        }
    }
}



// 方法2：数学规律
// https://leetcode.com/problems/number-of-digit-one/discuss/64390/AC-short-Java-solution

class Solution {
    public int countDigitOne(int n) {
        int count = 0;
          
        for (long k = 1; k <= n; k *= 10) {
          long r = n / k, m = n % k;
          // sum up the count of ones on every place k
          count += (r + 8) / 10 * k + (r % 10 == 1 ? m + 1 : 0);
        }
          
        return count;
      }
}