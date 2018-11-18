// 思路：直接遍历

class Solution {
    public boolean validMountainArray(int[] A) {
        if (A.length < 3 || A[1] <= A[0]) return false;
        int i = 1;
        while (i < A.length && A[i] > A[i - 1]) i++;
        if (i == A.length) return false; // always ascending
        while (i < A.length && A[i] < A[i - 1]) i++;
        return i == A.length;
    }
}