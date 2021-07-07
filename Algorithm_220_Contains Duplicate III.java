import java.util.TreeSet;

// 方法1：设当前元素为x，下标为i，用一个treeset来存储x之前的k的元素
// 即nums[i - k] ~ nums[i - 1]这些元素
// 维护一个长度为k的滑动窗口。如果treeset中大于等于x - t的第一个元素ceiling同时满足
// ceiling <= x + t，说明找到了满足条件的一对数字，返回true
// 注意这里只需要使用treeset而不用treemap的原因是，不可能出现重复元素被添加到set中的情况
// 因为如果出现了重复元素，则nums[i] == ceiling，已经满足了ceiling <= nums[i] + t
// 所以直接return true了
// 必须使用long作为treeset的元素类型，不然有可能溢出

class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int n = nums.length;
        TreeSet<Long> set = new TreeSet<Long>();
        for (int i = 0; i < n; i++) {
            // ceiling is the smallest element that satisfies ceiling >= nums[i] - t
            Long ceiling = set.ceiling((long) nums[i] - (long) t);
            if (ceiling != null && ceiling <= (long) nums[i] + (long) t) {
                return true;
            }
            set.add((long) nums[i]);
            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }
}

// 方法2：桶排序
// 对于元素x，其影响的区间是[x - t, x + t]，所以可以设桶的大小size为t + 1
// size = t + 1的本质是因为数轴上差值为t的两个数，在数轴上间隔的距离是t + 1
// 比如t = 3, [0, 1, 2, 3]这4个数需要被落入同一个桶
// 如果两个元素同属于同一个桶，则它们必然满足条件
// 如果两个元素属于相邻桶，需要检查这两个元素的差值是否小于等于t
// 如果两个元素不属于同一个桶也不属于相邻桶，则必然不满足条件
// 将每个整数x表示为x = (t + 1) * a + b (0 <= b <= t)的形式，则a就可以作为桶的下标
// 因为一个桶中至多只有1个元素，所以可以用HashMap实现
// https://leetcode-cn.com/problems/contains-duplicate-iii/solution/cun-zai-zhong-fu-yuan-su-iii-by-leetcode-bbkt/

class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int n = nums.length;
        Map<Long, Long> map = new HashMap<Long, Long>();
        long w = (long) t + 1;
        for (int i = 0; i < n; i++) {
            long id = getID(nums[i], w);
            if (map.containsKey(id)) { // 两元素在同一个桶内
                return true;
            }
            // 相邻桶
            if (map.containsKey(id - 1) && Math.abs(nums[i] - map.get(id - 1)) < w) {
                return true;
            }
            if (map.containsKey(id + 1) && Math.abs(nums[i] - map.get(id + 1)) < w) {
                return true;
            }
            map.put(id, (long) nums[i]);
            if (i >= k) {
                map.remove(getID(nums[i - k], w));
            }
        }
        return false;
    }

    public long getID(long x, long w) {
        if (x >= 0) {
            return x / w;
        }
        return (x + 1) / w - 1; // x为负数时
    }
}