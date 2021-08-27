// 方法：n为奇数和偶数两种情况分别讨论
// 1. n为偶数，其二进制表示的最后一位为0，直接右移
// 2. n为奇数。根据其二进制表示的最后两位为01和11再分开讨论
// 2.1. 最后2位为01。将n更新为n - 1后，最后2位变成00，可以连续右移2位而不遇到1。若将n更新为n + 1，则最后2位变成10，右移1位后，就要再次处理1，所以更新为n - 1能使操作次数更少
// 2.2. 最后2位为11。将n更新为n + 1后，最后2位变成00，可以连续右移2位而不遇到1。若将n更新为n - 1，则最后2位变成10，右移1位后，就要再次处理1，所以更新为n + 1能使操作次数更少
// n == 3是一个特殊情况。3的二进制表示为11，如果将3变成4，则整个过程为3->4->2->1。如果将3变成2，则整个过程为3->2->1。所以用2代替3更划算
// n == Integer.MAX_VALUE是另一个特殊情况。此时令n = n + 1后，n被更新为Integer.MIN_VALUE。无符号右移>>>31位后等于1，总操作次数为32次
// 如果使用有符号右移>>，则会进入死循环
// https://leetcode-cn.com/problems/integer-replacement/solution/java-0ms-100-shuang-o1-wei-yun-suan-by-noobhan/

class Solution {
    public int integerReplacement(int n) {
        int count = 0;
        while (n != 1) {
            //与运算判断最后一位来区分奇偶
            if ((n & 1) == 0) {
                //偶数直接无符号右移，
                //2147483647 会被奇数处理算法加一溢出为负数，
                //若选用带符号右移将无法回到1.
                n >>>= 1;
                count++;
            } else {
                //识别奇数的上一位是否为1，即 以 10 结尾(xxxx01)还是以11结尾(xxxx11)
                if ((n & 2) == 0) {
                    //01结尾最优则应当 用 n -1 取代 n
                    n -= 1;
                    count++;
                } else {
                    //11结尾除3这个特殊情况外，其余选用 n + 1取代 n，原因如上
                    if (n == 3) {
                        //3的特殊性处理，原因如上
                        count += 2;
                        break;
                    } else {
                        n += 1;
                    }
                    count++;
                }
            }
        }
        return count;
    }
}