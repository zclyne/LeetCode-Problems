// 思路：left、right分别代表container的左右壁，container的高度受制于left和right中较小的那个
// 初始时，left的right分别设为0和height.length - 1
// 如果left比right小，那么任何在right左侧、left右侧的都没有必要选择，因为宽度减小的同时，高度受制于left而不可能增加
// 因此，container的容积只有可能减小而不可能增大。因此一定要把left向右移，即left++
// 若right比left小，同理，必须将right左移，即right--
// 终止条件为left == right，此时container宽度为0

class Solution {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1, res = 0;
        while (left < right) {
            res = Math.max(res, (right - left) * Math.min(height[left], height[right]));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }
}