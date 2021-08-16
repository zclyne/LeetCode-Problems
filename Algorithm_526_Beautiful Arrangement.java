import java.util.HashSet;
import java.util.Set;

// 方法1：回溯法
// 设当前在perm中的下标为i。如果i == n + 1，说明整个permutation被构建完毕，当前permutation是符合条件的beautiful permutation
// 则result++，然后return
// 否则，从1到n遍历，设当前数字为j。若j之前没有被使用过（即!used.contains(j)）并且i % j == 0或j % i == 0
// 说明j可以被放置在perm[i]位置上，并且保持整个perm是beautiful的
// 将j加入used，然后向下一层继续递归
// 时间复杂度O(n!)

class Solution {

    private int result = 0;

    public int countArrangement(int n) {
        backtracking(n, 1, new HashSet<>());
        return result;
    }

    private void backtracking(int n, int i, Set<Integer> used) {
        if (i == n + 1) { // successfully found a beautiful permutation
            result++;
            return;
        }

        for (int j = 1; j <= n; j++) {
            if (!used.contains(j) && (j % i == 0 || i % j == 0)) { // j is not used before, and it can be put at perm[i]
                used.add(j);
                backtracking(n, i + 1, used);
                used.remove(j);
            }
        }
    }

}

// 方法2：状态压缩DP
// 由于1 <= n <= 15，所以可以用一个二进制数来表示各个数是否被使用
// 例如n = 4, 0101就表示数字1和3已经被使用，而2和4还未被使用
// f[mask]表示当前已经选择的数字由mask表示时，方案的总数
// 枚举当前mask中每一位1作为最后被选择的数值。由于我们是从小到大枚举mask的，所以计算f[mask]的所有
// 依赖的值都已经被提前计算完毕
// 设i + 1是最后一个被选择的数字，则mask & (1 << i) != 0
// num = Integer.bitCount(mask)，是mask中1的个数，也就代表了已经选择的数字数量，也就是当前perm中的下标
// 则能够构成beautiful permutation就要求num % (i + 1) == 0或(i + 1) % num == 0
// 最后一个选择的数是i + 1，则在选择i + 1之前的状态就对应于mask ^ (1 << i)，表示把mask的第i位置0
// 所以f[mask] += f[mask ^ (1 << i)]
// 最终f[1 << n - 1]就是答案
// 时间复杂度O(n * 2^n)

class Solution {
    public int countArrangement(int n) {
        int[] f = new int[1 << n];
        f[0] = 1;
        for (int mask = 1; mask < (1 << n); mask++) {
            int num = Integer.bitCount(mask);
            for (int i = 0; i < n; i++) { // 枚举mask中的每一位1，作为最后被选择的数字
                if ((mask & (1 << i)) != 0 && ((num % (i + 1)) == 0 || (i + 1) % num == 0)) {
                    f[mask] += f[mask ^ (1 << i)];
                }
            }
        }
        return f[(1 << n) - 1];
    }
}