import java.util.Arrays;
import java.util.stream.IntStream;

// greedy

// 方法1：排序后two pointer
// 比较两个数组的所有元素之和
// 对于较小的（nums1），从左到右扫描；较大的（nums2）从右到左扫描
// 对于nums1[i]和nums2[j]，要么将nums1[i]增大到6，要么将nums2[j]减小到1
// 比较6-nums1[i]和nums2[j] - 1的关系，取较大者
// 因为较大的那个能更好的弥补两数组的所有元素之和的差
// 直到nums1的所有元素之和大于等于nums2的所有元素之和
// O(mlogm + nlogn)

class Solution {
    public int minOperations(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if (m > 6 * n || n > 6 * m) { // cannot be satisfied
            return -1;
        }

        // get the sum of all elements
        int sum1 = IntStream.of(nums1).sum();
        int sum2 = IntStream.of(nums2).sum();
        if (sum1 > sum2) { // make sure sum1 always <= sum2
            return minOperations(nums2, nums1);
        }

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0, j = n - 1;
        int numOperations = 0;

        while (sum1 < sum2) {
            if (j < 0 || i < m && 6 - nums1[i] > nums2[j] - 1) { // it's better to increase nums1[i] to 6
                sum1 += 6 - nums1[i];
                i++;
            } else { // it's better to decrease nums2[j] to 1
                sum2 -= nums2[j] - 1;
                j--;
            }
            numOperations++;
        }

        return numOperations;
    }
}

// 方法2：同样先保证sum(nums1) <= sum(nums2)
// 然后遍历nums1和nums2，计算各数组中1~6每个数的数量
// 对于nums1中的每个1，最多可以将其增大到6，每个操作对于sum(nums1)贡献+5
// 对于nums2中的每个6，最多可以将其减小到1，每个操作对于sum(nums2)贡献-5
// 若nums1中的1和nums2中的6都用完了后sum(nums1)仍然<sum(nums2)
// 则继续考察nums1中的2和nums2中的4，以此类推
// 优化：nums1中的1可以看作nums2中的6，因为对于diff的贡献都是5
// nums1中的2和nums2中的4同理

class Solution2 {
    public int minOperations(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if (m > 6 * n || n > 6 * m) { // cannot be satisfied
            return -1;
        }

        // get the sum of all elements
        int sum1 = IntStream.of(nums1).sum();
        int sum2 = IntStream.of(nums2).sum();
        if (sum1 > sum2) { // make sure sum1 always <= sum2
            return minOperations(nums2, nums1);
        }

        int[] countNums1 = this.getNumsCount(nums1);
        int[] countNums2 = this.getNumsCount(nums2);

        int diff = sum2 - sum1;
        int i = 1, j = 6;
        int numOperations = 0;

        while (diff > 0) {
            // increment number i in nums1 to 6
            int curNumOp = Math.min(countNums1[i], diff / (6 - i) + (diff % (6 - i) != 0 ? 1 : 0));
            diff -= (6 - i) * curNumOp;
            numOperations += curNumOp;
            i++;
            if (diff <= 0) { // notice here
                break;
            }

            // decrease number j in nums2 to 1
            curNumOp = Math.min(countNums2[j], diff / (j - 1) + (diff % (j - 1) != 0 ? 1 : 0));
            diff -= (j - 1) * curNumOp;
            numOperations += curNumOp;
            j--;
        }

        return numOperations;
    }

    private int[] getNumsCount(int[] nums) {
        int[] count = new int[7];
        for (int num : nums) {
            count[num]++;
        }
        return count;
    }
}