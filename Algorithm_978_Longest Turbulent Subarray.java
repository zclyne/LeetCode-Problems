// 思路：双指针
// left和right分别记录当前所选择的子数组的左右边界
// lastDirection变量记录arr[right - 1]和arr[right]之间的大小关系
// 1表示递增，0表示相等，-1表示递减
// 每次循环都判断arr[right]和arr[right + 1]的大小关系，能否延续turbulent subarray的要求
// 如果能延续，则将right++
// 如果不能，则要相应地更新left的值，缩小滑动窗口的范围

class Solution {
    public int maxTurbulenceSize(int[] arr) {
        if (arr.length <= 1) {
            return arr.length;
        }
        int lastDirection = 0; // 1 for inc, -1 for desc, 0 for equal
        int left = 0, right = 0;
        int result = 1;
        while (right < arr.length - 1) {
            if (arr[right] == arr[right + 1]) {
                left = right + 1;
                lastDirection = 0;
            } else if (arr[right] > arr[right + 1]) {
                if (right > 0 && lastDirection != 1) { // last dir is not inc, not a valid turbulent subarray
                    left = right;
                }
                lastDirection = -1;
            } else { // arr[right] < arr[right + 1]
                if (right > 0 && lastDirection != -1) { // last dir is not desc, not a valid turbulent subarray
                    left = right;
                }
                lastDirection = 1;
            }
            right++;
            result = Math.max(result, right - left + 1);
        }
        return result;
    }
}