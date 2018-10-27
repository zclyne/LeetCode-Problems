// 先根据A[1]与A[0]的关系判断出该数组应该递增、递减还是无法确定，分别用isIncreasing = 1 / -1 / 0表示
// 然后遍历原数组，若isIncreasing为1时出现了相邻两个数递减，则直接返回false
// 若isIncreasing为-1时出现了相邻两个数递增，则也返回false
// 若isIncreasing仍未0，说明还没有确定该数组递增还是递减，需要根据A[i]和A[i- 1]来确定

class Solution {
    public boolean isMonotonic(int[] A) {
        if (A.length == 1) return true;
        int isIncreasing = 0; // 0 stands for A[i] == A[i - 1]
        if (A[1] > A[0]) isIncreasing = 1; // 1 stands for monotonic increasing
        else if (A[1] < A[0]) isIncreasing = -1; // -1 stands for monotonic decreasing
        for (int i = 1; i < A.length; i++) {
            if (isIncreasing == 1 && A[i] < A[i - 1] || isIncreasing == -1 && A[i] > A[i - 1])
                return false;
            else if (isIncreasing == 0) {
                if (A[i] > A[i - 1]) isIncreasing = 1;
                else if (A[i] < A[i - 1]) isIncreasing = -1;
            }
        }
        return true;
    }
}