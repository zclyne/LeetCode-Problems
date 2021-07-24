import java.util.Arrays;

// 方法1：动态规划
// dp[i]表示在最外层信封选择第i个信封时，所能够嵌套的最大数量的信封总数
// 首先将envelopes按照尺寸从小到大排序
// 然后遍历envelopes，对于第i个信封，只需要0 <= j < i这些信封，求得最大的dp[i]即可
// 本质是最长上升子序列问题

class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

        int result = 1;
        int[] dp = new int[envelopes.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < envelopes.length; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
                    // can put the j th envelope into the i th envelope
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            result = Math.max(result, dp[i]);
        }

        return result;
    }
}

// 方法2：二分查找+动态规划
// 首先对envelopes排序。按照width升序、width相同时height降序的顺序来排序
// width相同时，height需要降序的原因是必须要保证对于每一个width，最多只能选择一个信封
// 当width相同、height降序排列时，对于一个width，它对应的信封的height值不可能组成长度超过1的严格递增序列
// 从根本上保证了对于一个width，只选择一个信封
// 然后对envelopes进行遍历，设当前遍历到的下标是i
// 数组f的第j个元素f[j]的含义是：envelopes的前i个元素中，可以组成总长度为j的嵌套信封的最后一个envelope的height
// 也就代表着，这j个嵌套信封的width和height都是严格递增序列
// 如果不存在长度为j的严格递增序列，则对应的f无定义
// 在进行状态转移时，考虑当前的h(i)，即第i个信封的height
// 1. 如果h(i)大于f中的最大值，说明可以把当前信封套在f的最大值对应的信封之外，形成一个长度更长的严格递增序列
// 2. 否则，从f中找到严格小于h(i)的最大元素f[j]，即f[j] < h(i) <= f[j + 1]，那么h(i)可以套在f[j]对应的信封之外
// 形成一个长度为j + 1的严格递增子序列，所以，更新f[j + 1] = h(i)
// 在f上进行二分查找，提高查询速度
// https://leetcode-cn.com/problems/russian-doll-envelopes/solution/zui-chang-shang-sheng-zi-xu-lie-bian-xin-6s8d/

class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }
        
        int n = envelopes.length;
        
        // 按照width升序、width相同时height降序的规则对envelopes排序
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] e1, int[] e2) {
                if (e1[0] != e2[0]) {
                    return e1[0] - e2[0];
                } else {
                    return e2[1] - e1[1];
                }
            }
        });

        List<Integer> f = new ArrayList<Integer>();
        f.add(envelopes[0][1]);
        for (int i = 1; i < n; ++i) {
            int width = envelopes[i][1];
            if (width > f.get(f.size() - 1)) { // 当前信封的宽度比之前嵌套信封数最多的最外层信封的宽度更大
                f.add(width);
            } else {
                int index = binarySearch(f, width);
                f.set(index, width);
            }
        }
        return f.size();
    }

    public int binarySearch(List<Integer> f, int target) {
        int low = 0, high = f.size() - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (f.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}