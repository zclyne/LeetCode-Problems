import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

// 方法：单调栈
// 顺序遍历nums2，并维护一个单调递减的单调栈
// 遍历过程中，对于当前元素cur，从栈中不断pop出比cur小的元素，直到栈顶元素大于cur或栈为空
// 由于是从左向右遍历的，所以栈中的元素都在cur左侧
// 这些元素被pop就说明cur是这些元素的next greater element
// 用map把他们存储下来
// nums2遍历完成后，再遍历一遍nums1。从map中获取nums1中各元素的next greater element
// 如果不存在，则默认值为-1

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Deque<Integer> stack = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums2.length; i++) {
            int cur = nums2[i];
            while (!stack.isEmpty() && stack.peek() < cur) {
                int top = stack.pop();
                map.put(top, cur); // cur is the next greater element of top
            }
            stack.push(cur);
        }

        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.getOrDefault(nums1[i], -1);
        }

        return result;
    }
}