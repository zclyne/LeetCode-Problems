// 方法；因为测试用例的数量级都很小，所以可以直接嵌套循环，遍历解决

class Solution {
    public boolean isCovered(int[][] ranges, int left, int right) {
        for (int n = left; n <= right; n++) {
            boolean notCovered = true;
            for (int[] range : ranges) {
                if (range[0] <= n && n <= range[1]) {
                    notCovered = false;
                    break;
                }
            }
            if (notCovered) {
                return false;
            }
        }
        return true;
    }
}

// 方法2：排序
// 每次拿到一个区间[l, r]，如果l <= left <= r，则说明[left, r]区间都已经被覆盖
// 令left = r + 1，继续取区间
// 直到最后left > right

class Solution {
    public boolean isCovered(int[][] ranges, int left, int right) {
        Arrays.sort(ranges, (a1, a2) -> a1[0] - a2[0]);
        for(int[] range: ranges) {
            int l = range[0];
            int r = range[1];
            if(l <= left && left <= r) {
                left = r + 1;
            }
        }
        return left > right;
    }
}

// 方法3：差分数组
// 用差分数组diff维护相邻两个整数的被覆盖区间数量变化量
// diff[i]对应覆盖整数i的区间数量相对于覆盖i - 1的区间数量变化量
// 这样，当遍历到闭区间[l, r]时，l相对于l - 1被覆盖区间数量多1，r + 1相对于r被覆盖区间数量少1
// 即diff[i] + 1, diff[r + 1] - 1
// 在维护完差分数组diff后，我们遍历diff，求前缀和，得出覆盖每个整数的区间数量
// 下标i对应的被覆盖区间数量即为初始数量0加上[1, i]闭区间的变化量之和
// 在计算被覆盖区间数量的同时，我们可以一并判断[left,right]闭区间内的所有整数是否都被覆盖

class Solution {
    public boolean isCovered(int[][] ranges, int left, int right) {
        int[] diff = new int[52];   // 差分数组
        for (int[] range : ranges) {
            ++diff[range[0]];
            --diff[range[1] + 1];
        }
        // 前缀和
        int curr = 0;
        for (int i = 1; i <= 50; ++i) {
            curr += diff[i];
            if (i >= left && i <= right && curr == 0) { // i在区间[left, right]内，并且没有区间覆盖到i
                return false;
            }
        }
        return true;
    }
}