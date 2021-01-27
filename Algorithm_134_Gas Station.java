// 思路：设从加油站i开始，最远可以到达加油站j
// 那么有结论：从任何i和j之间的加油站出发，最远都不可能到达比j更远的加油站
// 证明：假设从加油站k出发（i < k < j)能够到达加油站l（l < j）
// 由于从i出发能够到达j，因此从i出发也能够到达k
// 所以从i出发能够到达l，和结论矛盾
// 所以在遍历过程中，如果发现从当前的i开始最远只能到达j，那么下一个起点可以直接选择为j + 1
// 而不需要考虑i和j之间的任意一个k

class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int remainGas = 0;
        int i = 0, count = 1;
        while (i < n) {
            int j = i;
            count = 1;
            remainGas = gas[i] - cost[i];
            while (remainGas >= 0) {
                j = (j + 1) % n;
                if (j == i) { // finished a round
                    return i;
                }
                remainGas = remainGas + gas[j] - cost[j];
            }
            if (j < i) { // end station comes back to a previous index which has been computed, this means no solution
                return -1;
            }
            i = j + 1;
        }
        return -1;
    }
}