import java.util.ArrayList;
import java.util.List;

// 方法：设要求的两个数为a和b。先对nums中的所有数做异或，得到的结果就是a ^ b，记为x
// 找到x中某个等于1的位，表示a和b在这个位上一个是0，一个是1
// 然后对nums中的所有数字按照这一位上是否为1进行分组，为1的分进同一组，为0的分进另一组
// 对于nums中除a、b以外的其他数字，由于每个数字都出现了2次，所以2个数字都会被分进同一组
// 而又由于a和b在这一位上的值不同，所以a和b一定不会被分进同一组
// 所以最终，每组内分别包含了a、b本身，以及其他一些出现了2次的数字
// 再对两个组内各自做一次所有数字的异或，就能得到结果
// https://leetcode-cn.com/problems/single-number-iii/solution/zhi-chu-xian-yi-ci-de-shu-zi-iii-by-leet-4i8e/

class Solution {
    public int[] singleNumber(int[] nums) {
        int allXOR = 0;
        for (int num : nums) {
            allXOR ^= num;
        }

        // look for the lowest digit that equals to 1
        int mask = 1;
        while ((allXOR & mask) == 0) {
            mask <<= 1;
        }

        List<Integer> groupA = new ArrayList<>();
        List<Integer> groupB = new ArrayList<>();
        int a = 0, b = 0;
        for (int num : nums) {
            if ((num & mask) == 0) { // num belongs to group A
                a ^= num;
            } else { // num belongs to group B
                b ^= num;
            }
        }
        
        return new int[]{a, b};
    }
}