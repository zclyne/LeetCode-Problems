// 方法：按照数字位数的不同来分开讨论
// 1位数只有1~9，总共占用了9个digit
// 2位数有10~99，总共占用了2 * 90 = 180个digit
// 3位数有100~999，总共占用了3 * 900 = 2700个digit
// ...以此类推，填充数组numDigitsArr
// 其中numDigitsArr[i]表示i位数的总共的digit数量
// startingNumArr[i]表示i位数的第一个数。例如i = 1对应为1，i = 2对应为10，i = 3对应为100...
// 变量numDigits记录第n个digit所对应的数字的位数
// 遍历numDigitsArr。如果当前的n大于numDigitsArr[numDigits]，说明n对应的数字的位数大于当前的numDigits
// 因此把numDigitsArr[numDigits]从n中扣除，然后numDigits++
// 当退出while循环时，说明已经找到了相应的数字的位数
// 然后将n - 1。这是由于原本n是从1开始的，减1后变成从0开始，便于处理
// 令idxOfNumber = n / numDigits，表示n对应的数是第几个数
// biasInNumber = n % numDigits，表示n在数字内是第几个digit
// 则startingNumArr[numDigits] + idxOfNumber就是n实际所在的数字，记为finalNumber
// finalNumber中从左往右数第biasInNumber位就是最终要求的位

class Solution {
    public int findNthDigit(int n) {
        int[] numDigitsArr = new int[]{0, 9, 180, 2700, 36000, 450000, 5400000, 63000000, 720000000};
        int[] startingNumArr = new int[]{0, 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000};
        int numDigits = 1;
        while (numDigits < 9 && n > numDigitsArr[numDigits]) {
            n -= numDigitsArr[numDigits];
            numDigits++;
        }
        n--; // make n start from 0
        int idxOfNumber = n / numDigits, biasInNumber = n % numDigits;
        int finalNumber = startingNumArr[numDigits] + idxOfNumber;
        int count = numDigits - biasInNumber - 1;
        while (count > 0) {
            finalNumber /= 10;
            count--;
        }
        return finalNumber % 10;
    }
}