// 思路：一个int共有32位，对于每一个bit，记录nums中所有数字在这一个bit上的1的出现次数
// 总次数%3得到的结果只有可能是0或1，若为0，表示唯一出现了一次的那个数在这一位上为0
// 若为1，表示唯一出现了一次的那个数在这一位上为1
// 把这一位恢复到result中，最终result的值就是所要找的数的值

class Solution {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            int numOfOnesOnThisBit = 0;
            for (int num : nums) {
                numOfOnesOnThisBit += (num >> i) & 1;
            }
            numOfOnesOnThisBit %= 3;
            result |= numOfOnesOnThisBit << i;
        }
        return result;
    }
}