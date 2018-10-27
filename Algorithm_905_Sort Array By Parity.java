// 思路：用两个指针start和end分别从数组A的首部和尾部对A做遍历，当start与end相遇时循环停止
// 当A[start]为奇数而A[end]为偶数时，将两个元素互换则恰好满足条件，调用swap函数，并将start++，end--
// 当A[start]与A[end]都为奇数时，A[start]需要被交换而A[end]满足条件，将end--，start不变
// 当A[start]与A[end]都为偶数时，A[end]需要被交换而A[start]满足条件，将start++，end不变
// 当A[start]为偶数而A[end]为奇数时，两者都满足条件，直接start++并end--

class Solution {
    public int[] sortArrayByParity(int[] A) {
        if (A.length == 1) return A;
        int start = 0, end = A.length - 1;
        while (start < end) {
            if ((A[start] & 1) == 1 && (A[end] & 1) == 0) { // A[start] is odd and A[end] is even, swap
                swap(A, start++, end--);
            } else if ((A[start] & 1) == 1 && (A[end] & 1) == 1) { // A[start] and A[end] are both odd
                end--;
            } else if ((A[start] & 1) == 0 && (A[end] & 1) == 0) { // A[start] and A[end] are both even
                start++;
            } else { // A[start] is even and A[end] is odd
                start++;
                end--;
            }
        }
        return A;
    }
    public void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}