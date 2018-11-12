// 思路：对每一个A[i]，寻找以A[i]结尾且A[i]为唯一最小值（不允许重复）的subarray的最大长度，记在left[i]中
// 再寻找以A[i]起始，且A[i]为最小值之一（允许重复）的subarray的最大长度，记在right[i]中
// 注意此处必须一边不允许重复，另一边允许重复
// 则以A[i]为最小值的所有subarray的个数为left[i] * right[i]个
// 这些subarray对sum的贡献为A[i] * left[i] * right[i]
// 具体的寻找这两个subarray的方法：monotonous increase stack
// 两个栈中存储的数组设为temp，temp[0]记录具体的值，temp[1]记录该值在数组A中的下标

class Solution {
    public int sumSubarrayMins(int[] A) {
        int[] left = new int[A.length]; // the distance between A[i] and its PLE(previous less element)
        int[] right = new int[A.length]; // the distance between A[i] and its NLE(next less element)
        Stack<int[]> PLEStack = new Stack<>();
        Stack<int[]> NLEStack = new Stack<>();
        // look for PLE
        for (int i = 0; i < A.length; i++) {
            while (!PLEStack.isEmpty() && PLEStack.peek()[0] > A[i])
                PLEStack.pop();
            left[i] = PLEStack.isEmpty() ? i + 1 : i - PLEStack.peek()[1];
            PLEStack.push(new int[] {A[i], i});
        }
        // look for NLE
        for (int i = A.length - 1; i >= 0; i--) {
            while (!NLEStack.isEmpty() && NLEStack.peek()[0] >= A[i])
                NLEStack.pop();
            right[i] = NLEStack.isEmpty() ? A.length - i : NLEStack.peek()[1] - i;
            NLEStack.push(new int[] {A[i], i});
        }
        int sum = 0, mod = 1000000007;
        for (int i = 0; i < A.length; i++)
            sum = (sum + left[i] * right[i] * A[i]) % mod;
        return sum;
    }
}