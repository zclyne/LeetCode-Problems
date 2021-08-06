import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

// 方法1：小顶堆
// 初始状态下，先把nums1中的所有元素和nums2[0]配对，并加入小顶堆pq。注意加入的是下标，而不是元素值本身
// 然后每次取堆顶元素pair，将nums1[pair[0]], nums2[pair[1]]加入结果result中
// 如果pair[1] + 1 < n，说明可以把这个pair的在nums2中的指针向后移动一格，因此把pair[0], pair[1] + 1加入到pq中
// 直到最后result中包含了k个元素，或pq为空为止

class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] pair1, int[] pair2) {
                return nums1[pair1[0]] + nums2[pair1[1]] - (nums1[pair2[0]] + nums2[pair2[1]]);
            }
        });

        int m = nums1.length, n = nums2.length;
        for (int i = 0; i < m; i++) {
            pq.add(new int[]{i, 0});
        }

        List<List<Integer>> result = new ArrayList<>();
        while (result.size() < k && !pq.isEmpty()) {
            int[] pair = pq.poll();
            result.add(Arrays.asList(nums1[pair[0]], nums2[pair[1]]));
            if (pair[1] < n - 1) {
                pq.offer(new int[]{pair[0], pair[1] + 1});
            }
        }

        return result;
    }
}


// 方法2：大顶堆暴力解，TLE
// 维护一个大小为k的大顶堆pq，直接嵌套遍历nums1和nums2中的所有元素，把pair加入到pq中
// 没有利用到nums1, nums2已经有序的性质

class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] pair1, int[] pair2) {
                return pair2[0] + pair2[1] - (pair1[0] + pair1[1]);
            }
        });

        for (int n1 : nums1) {
            for (int n2 : nums2) {
                if (pq.size() < k) {
                    pq.offer(new int[]{n1, n2});
                } else {
                    int[] top = pq.peek();
                    if (n1 + n2 < top[0] + top[1]) {
                        pq.poll();
                        pq.offer(new int[]{n1, n2});
                    }
                }
            }
        }
        
        List<List<Integer>> result = new LinkedList<>();
        while (result.size() < k && !pq.isEmpty()) {
            int[] p = pq.poll();
            result.add(0, Arrays.asList(p[0], p[1]));
        }

        return result;
    }
}

