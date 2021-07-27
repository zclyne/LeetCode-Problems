import java.util.ArrayList;
import java.util.List;

// 方法：考虑a = 2, b = [1234]
// 则a^b = 2^1234 = 2^4 * 2^1230 = 2^4 * (2^123)^10
// 自定义calcPow(int a, int k)方法，用于计算a^k，并且0 <= k <= 10
// 则可以递归地解决这个问题
// 每次取b的最后一个元素，设为last，用calcPow方法计算2^last
// 把last从b中移除，对于b中的剩余元素，递归地调用superPow来计算a^b。由于int[]无法实现移除元素，所以用helper来辅助递归
// 对于新得到的b，递归调用superPow计算得到a^b后，还要对其取10次方，同样使用calcPow方法来计算
// 还要注意到这样一个性质：(a * b) % k == ((a % k) * (b % k)) % k
// 所以对于任意的乘法，可以先对两个操作数进行取模，然后再想乘，乘完后再取模，最终的结果相同
// 这个性质的证明可参考下面链接：
// https://leetcode-cn.com/problems/super-pow/solution/you-qian-ru-shen-kuai-su-mi-suan-fa-xiang-jie-by-l/

class Solution {

    private final int MOD = 1337;

    public int superPow(int a, int[] b) {
        return helper(a, b, b.length - 1);
    }

    private int helper(int a, int[] b, int pos) {
        if (pos == -1) {
            return 1;
        }
        int last = b[pos];

        return ((calcPow(a, last) % MOD) * (calcPow(helper(a, b, pos - 1), 10) % MOD)) % MOD;
    }

    private int calcPow(int a, int k) {
        int result = 1;
        a %= MOD;
        while (k > 0) {
            result *= a;
            result %= MOD;
            k--;
        }
        return result;
    }
}