// 方法1：二分查找
// 根据题意，matrix[0][0]和matrix[n - 1][n - 1]分别是整个matrix中的最小值和最大值，记为l和r
// 在l和r范围内进行二分查找，设mid = (l + r) / 2
// 从matrix的左下角，即matrix[n - 1][0]开始，计算matrix中小于等于mid的元素的总数
// 具体计算方法即countElementLessThanOrEqualToTarget。设当前访问的是matrix[i][j]
// 如果matrix[i][j] <= mid，就右移一位，即j++
// 否则，matrix[i]行的j左边的元素全部小于mid，count += j
// 直到最后i < 0为止。这个计算过程的时间复杂度是O(n)
// 设matrix中小于等于mid的元素总数是count，则有以下两种情况：
// 1. count < k。说明要找的target一定比mid更大，并且不包含mid，left = mid + 1
// 2. count >= k。说明要找的target比mid小，也有可能等于mid，right = mid
// 直到最后left == right时退出循环，target == left == right
// 由于二分查找的时间复杂度是O(log(r - l))，所以算法整体的时间复杂度是O(nlog(r - l))

class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0], right = matrix[n - 1][n - 1];

        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = countElementLessThanOrEqualToTarget(matrix, mid);
            if (count < k) { // target is in the bigger part, excluding mid itself
                left = mid + 1;
            } else { // target is in the smaller part, including mid itself
                right = mid;
            }
        }

        return left;
    }

    private int countElementLessThanOrEqualToTarget(int[][] matrix, int target) {
        int n = matrix.length;
        int i = n - 1, j = 0;
        int count = 0;
        while (i >= 0) {
            if (j < n && matrix[i][j] <= target) { // move to the right grid
                j++;
            } else { // j == n or matrix[i][j] > target
                count += j;
                i--; // move to the upper grid
            }
        }
        return count;
    }
}



// 方法2：归并排序
// 矩阵的每一行都是一个有序数组，问题可以转化为归并n个有序数组，并找到第k小的元素
// 具体的归并过程用小顶堆来实现

class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{matrix[i][0], i, 0});
        }
        for (int i = 0; i < k - 1; i++) {
            int[] now = pq.poll();
            if (now[2] != n - 1) {
                pq.offer(new int[]{matrix[now[1]][now[2] + 1], now[1], now[2] + 1});
            }
        }
        return pq.poll()[0];
    }
}