// 注意若n是偶数，要排除0，并且i--

class Solution {
    public int[] sumZero(int n) {
        int[] res = new int[n];
        int num = -(n / 2);
        for (int i = 0; i < n; i++) {
            if (num == 0 && n % 2 == 0) {
                i--;
                num++;
                continue;
            }
            res[i] = num;
            num++;
        }
        return res;
    }
}