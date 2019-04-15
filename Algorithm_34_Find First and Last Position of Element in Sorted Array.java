// My Solution
// 思路：先使用二分查找找到target在原数组中的一个位置，然后线性向左右两边扩展
// 用start和end来记录target的起始下标和终止下标，最后返回{start, end}
// 若未找到，返回{-1, -1}

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                int start = mid, end = mid;
                while (start > 0 && nums[start - 1] == nums[mid]) {
                    start--;
                }
                while (end < nums.length - 1 && nums[end + 1] == nums[mid]) {
                    end++;
                }
                return new int[] {start, end};
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return new int[] {-1, -1};
    }
}

// Better Solution
// 思路：先找 >= target的第一个元素出现的位置，再找 >= target+1的第一个元素出现的位置

class BetterSolution {
    public int[] searchRange(int[] A, int target) {
        int start = firstGreaterEqual(A, target);
		if (start == A.length || A[start] != target) {
			return new int[]{-1, -1};
		}
		return new int[]{start, firstGreaterEqual(A, target + 1) - 1};
    }
    
	//find the first number that is greater than or equal to target.
	//could return A.length if target is greater than A[A.length-1].
	//actually this is the same as lower_bound in C++ STL.
	private int firstGreaterEqual(int[] A, int target) {
		int low = 0, high = A.length;
		while (low < high) {
			int mid = low + ((high - low) >> 1);
			//low <= mid < high
			if (A[mid] < target) {
				low = mid + 1;
			} else {
				//should not be mid-1 when A[mid]==target.
				//could be mid even if A[mid]>target because mid<high.
				high = mid;
			}
		}
        return low;
    }
}