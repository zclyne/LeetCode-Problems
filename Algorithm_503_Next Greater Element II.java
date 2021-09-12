import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

// 方法：单调栈
// 顺序遍历nums数组2遍，并在遍历过程中维护一个单调递减的单调栈
// 基本思路与Algorithm_456相同
// 由于nums是循环数组，所以循环次数上限不是n，而是2 * n
// 另外，单调栈中不仅存储数字本身，还要存储下标，因此栈的元素是一个int[]，而不是int
// 遍历过程中，设当前下标为index，则index = i % n
// 当前元素值为cur = nums[index]
// 如果cur大于栈顶元素的元素值，则不断从栈中pop出元素，直到栈为空或栈顶元素值大于cur为止
// 由于栈中已经存储了元素对应的下标，因此可以直接将result中对应下标的值设置成cur，即cur是该元素的next greater element
// 而result初始化成所有元素值都为-1，就使得最终遍历完成后，result中没有被访问到的元素都是-1
// 表示这些元素不存在next greater element

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);

        Deque<int[]> stack = new LinkedList<>();
        for (int i = 0; i < 2 * n; i++) {
            int index = i % n;
            int cur = nums[index];
            while (!stack.isEmpty() && stack.peek()[0] < cur) {
                int[] top = stack.pop();
                result[top[1]] = cur;
            }
            stack.push(new int[]{cur, index});
        }

        return result;
    }
}