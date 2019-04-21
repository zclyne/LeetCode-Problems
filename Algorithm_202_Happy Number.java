import java.util.HashSet;

// My Solution
// 思路：visited记录已经访问过的所有数字，while循环中先查看当前的n是否已经存在在visited中，若存在，则不是happy number，返回false
// 若不存在，按照happy number的规则分解n并存在newN中，然后把newN赋给n，继续循环

class Solution {
    public boolean isHappy(int n) {
        HashSet<Integer> visited = new HashSet<>();
        while (n != 1) {
            if (visited.contains(n)) { // loop
                return false;
            }
            visited.add(n);
            int newN = 0;
            while (n != 0) {
                newN += (n % 10) * (n % 10);
                n /= 10;
            }
            n = newN;
        }
        return true;
    }
}

// Double Pointer Solution
// 思路：类似于判断链表是否有环，用slow和fast两个指针。不是happy number等价于链表有环，因此slow和fast最终会相遇
// 若是happy number，则最终fast一定会到达1，并始终停留在1直到slow追上fast
// 因此最终根据fast是否为1就可以判断出n是否为happy number

class DoublePointerSolution {

    private int getDigitSum(int n) {
        int res = 0;
        while (n != 0) {
            res += (n % 10) * (n % 10);
            n /= 10;
        }
        return res;
    }

    public boolean isHappy(int n) {
        int slow = n, fast = n;
        do {
            slow = getDigitSum(slow);
            fast = getDigitSum(getDigitSum(fast));
        } while (slow != fast);
        return fast == 1;
    }

}