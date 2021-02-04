// 解法1：2个优先级队列+延迟删除
// 思路：https://leetcode-cn.com/problems/sliding-window-median/solution/hua-dong-chuang-kou-zhong-wei-shu-by-lee-7ai6/
// small的元素要么和large元素个数相等，要么正好比large多一个
// 在个数相等情况下，滑窗的大小为偶数，所以两堆顶元素之和 / 2就是中位数
// 若small中的元素个数比large恰好多1，则滑窗的大小为奇数，small的堆顶元素就是中位数
// 因为堆不支持删除非栈顶元素的操作，所以用延迟删除的策略来处理
// 对元素的删除操作进行标记，只有当这个元素出现在堆顶时，才真正将它删除掉
// 因为非栈顶元素不影响中位数的计算，所以这种方法对结果没有影响

class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        DualHeap dh = new DualHeap(k);
        for (int i = 0; i < k; ++i) {
            dh.insert(nums[i]);
        }
        double[] ans = new double[nums.length - k + 1];
        ans[0] = dh.getMedian();
        for (int i = k; i < nums.length; ++i) {
            dh.insert(nums[i]);
            dh.erase(nums[i - k]);
            ans[i - k + 1] = dh.getMedian();
        }
        return ans;
    }
}

class DualHeap {
    // 大根堆，维护较小的一半元素
    private PriorityQueue<Integer> small;
    // 小根堆，维护较大的一半元素
    private PriorityQueue<Integer> large;
    // 哈希表，记录「延迟删除」的元素，key 为元素，value 为需要删除的次数
    private Map<Integer, Integer> delayed;

    private int k;
    // small 和 large 当前包含的元素个数，需要扣除被「延迟删除」的元素
    private int smallSize, largeSize;

    public DualHeap(int k) {
        this.small = new PriorityQueue<Integer>(new Comparator<Integer>() {
            public int compare(Integer num1, Integer num2) {
                return num2.compareTo(num1);
            }
        });
        this.large = new PriorityQueue<Integer>(new Comparator<Integer>() {
            public int compare(Integer num1, Integer num2) {
                return num1.compareTo(num2);
            }
        });
        this.delayed = new HashMap<Integer, Integer>();
        this.k = k;
        this.smallSize = 0;
        this.largeSize = 0;
    }

    public double getMedian() {
        return (k & 1) == 1 ? small.peek() : ((double) small.peek() + large.peek()) / 2;
    }

    public void insert(int num) {
        if (small.isEmpty() || num <= small.peek()) {
            small.offer(num);
            ++smallSize;
        } else {
            large.offer(num);
            ++largeSize;
        }
        makeBalance();
    }

    public void erase(int num) {
        delayed.put(num, delayed.getOrDefault(num, 0) + 1);
        if (num <= small.peek()) {
            --smallSize;
            if (num == small.peek()) {
                prune(small);
            }
        } else {
            --largeSize;
            if (num == large.peek()) {
                prune(large);
            }
        }
        makeBalance();
    }

    // 不断地弹出 heap 的堆顶元素，并且更新哈希表
    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty()) {
            int num = heap.peek();
            if (delayed.containsKey(num)) {
                delayed.put(num, delayed.get(num) - 1);
                if (delayed.get(num) == 0) {
                    delayed.remove(num);
                }
                heap.poll();
            } else {
                break;
            }
        }
    }

    // 调整 small 和 large 中的元素个数，使得二者的元素个数满足要求
    private void makeBalance() {
        if (smallSize > largeSize + 1) {
            // small 比 large 元素多 2 个
            large.offer(small.poll());
            --smallSize;
            ++largeSize;
            // small 堆顶元素被移除，需要进行 prune
            prune(small);
        } else if (smallSize < largeSize) {
            // large 比 small 元素多 1 个
            small.offer(large.poll());
            ++smallSize;
            --largeSize;
            // large 堆顶元素被移除，需要进行 prune
            prune(large);
        }
    }
}

// 解法2：二分查找+冒泡排序
// 维护一个已排序的滑动窗口数组
// 使用二分查找来检索要删除的元素的索引
// 将需要删除的元素替换为需要插入的元素
// 使用冒泡的方式维护数组的有序性

class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] res = new double[nums.length - k + 1];
        int[] window = new int[k];
        //添加初始值
        for (int i = 0; i < k; i++) {
            window[i] = nums[i];
        }
        //初始的排序
        Arrays.sort(window);
        res[0] = getMid(window);
        //窗口滑动
        for (int i = 0; i < nums.length - k; i++) {
            //需要删除的数
            int index = search(window, nums[i]);
            //替换为需要插入的数
            window[index] = nums[i + k];
            //向后冒泡
            while (index < window.length - 1 && window[index] > window[index + 1]) {
                swap(window, index, index + 1);
                index++;
            }
            //向前冒泡
            while (index > 0 && window[index] < window[index - 1]) {
                swap(window, index, index - 1);
                index--;
            }
            res[i + 1] = getMid(window);
        }
        return res;
    }

    //交换
    private void swap(int[] window, int i, int j) {
        int temp = window[i];
        window[i] = window[j];
        window[j] = temp;
    }

    //求数组的中位数
    private double getMid(int[] window) {
        int len = window.length;
        if (window.length % 2 == 0) {
            //避免溢出
            return window[len / 2] / 2.0 + window[len / 2 - 1] / 2.0;
        } else {
            return window[len / 2];
        }
    }

    //最简单的二分查找
    private int search(int[] window, int target) {
        int start = 0;
        int end = window.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (window[mid] > target) {
                end = mid - 1;
            } else if (window[mid] < target) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}