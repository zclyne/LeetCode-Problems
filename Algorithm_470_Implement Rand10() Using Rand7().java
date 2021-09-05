/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 * @return a random integer in the range 1 to 7
 */

// 方法1：先不断调用rand7()，直到生成了一个1~5之间的数字
// 然后将其乘2
// 再不断调用rand7()，直到得到的结果不等于4
// 如果结果小于4，则将乘2的结果-1，可以得到[1, 3, 5, 7, 9]
// 如果结果大于4，则不作操作，可以得到[2, 4, 6, 8, 10]
// 最终把结果返回，则1~10中所有数字的出现概率是相同的

class Solution extends SolBase {
    public int rand10() {
        int num = rand7();
        while (num > 5) {
            num = rand7();
        }
        int flag = rand7();
        while (flag == 4) {
            flag = rand7();
        }
        int minus = flag < 4 ? 1 : 0;
        return num * 2 - minus;
    }
}

// 方法2：(rand7() - 1) * 7 + rand7()可以得到一个[1, 49]范围内均匀分布的随机数
// 取其[1, 40]的部分，将其对10取模并加1，就能得到[1, 10]范围内均匀分布的随机数
// 注意：(randX() - 1)*Y + randY()可以生成范围在[1, X * Y]范围内均匀分布的随机数

class Solution extends SolBase {
    public int rand10() {
        // 首先得到一个数
        int num = (rand7() - 1) * 7 + rand7();
        // 只要它还大于40，那你就给我不断生成吧
        while (num > 40)
            num = (rand7() - 1) * 7 + rand7();
        // 返回结果，+1是为了解决 40%10为0的情况
        return 1 + num % 10;
    }
}