import java.util.ArrayList;

// 思路：用数组nums存储1~n所有数字，变量factorial记录从当前位的后一位开始，剩余数字的全排列个数，初始时factorial = (n - 1)!，res存放结果
// 以n = 3, k = 3为例，nums = {1, 2, 3}, 
// res的长度应该为3，res[0]之后共有2位，factorial = 2! = 2，表示除去res[0]之后，剩余数字的全排列个数是2
// 用k - 1除以factorial，可以得出应该放在res[0]位置上的数字在数组nums中的下标
// 此处为2 / 2 = 1，nums[1] = 2，res[0] = '2'
// indexOfNumToAdd * factorial表示被跳过的全排列个数，此处表示res[0] = '1'的2种全排列"123"和"132"都不可能
// 把该数字从k中扣除，得到的新的k记为k_new，表示在后续n - 1个数字的全排列中，选择第k_new个排列
// 通过nums.remove()把已经使用过的数字从nums中删除
// 以此类推，知道把n个数字全部加入res为止

class Solution {
    public String getPermutation(int n, int k) {
        StringBuilder res = new StringBuilder();
        k--; // 0-index
        int factorial = 1;
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nums.add(i + 1);
            factorial *= i + 1;
        }
        factorial /= n; // initially, factorial = (n - 1)!
        for (int i = 0; i < n; i++) {
            int indexOfNumToAdd = k / factorial;
            res.append((char) ('0' + nums.get(indexOfNumToAdd)));
            k -= indexOfNumToAdd * factorial;
            nums.remove(indexOfNumToAdd);
            factorial /= (n - i - 1) > 0 ? n - i - 1 : 1; // handle i == n - 1
        }
        return res.toString();
    }
}