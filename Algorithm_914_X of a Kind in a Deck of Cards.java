// 思路：用一个HashMap记录下数组deck中所有数字出现的次数，并求所有数的最大公因数，即为X的取值
// 若该最大公约数为1，则不满足条件，返回false，否则返回true

import java.util.HashMap;

class Solution {
    public boolean hasGroupsSizeX(int[] deck) {
        int res = 0;
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int num : deck) count.put(num, count.getOrDefault(num, 0) + 1);
        for (int num : count.values()) res = gcd(num, res);
        return res > 1;
    }
    public int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}