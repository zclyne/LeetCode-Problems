import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 方法1：计数
// 设arr排序后的数组为sorted，则对于一个子数组arr[i ~ j]
// 如果sorted[i ~ j]中的所有元素和arr[i ~ j]中所有元素都相同，则找到了一个chunk
// 因此可以用map来进行计数。对于sorted中的每个数字，map中的value + 1
// 对于arr中的每个数字，map中的value - 1
// 当某个元素的value为0时，就将其从map中删除
// 所以当map为空时，说明找到了这样的一个j。result++

class Solution {
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        int result = 0;
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            // for the current element in sorted, value + 1
            int value1 = map.getOrDefault(sorted[i], 0) + 1;
            if (value1 == 0) {
                map.remove(sorted[i]);
            } else {
                map.put(sorted[i], value1);
            }
            // for the current element in the original arr, value - 1
            int value2 = map.getOrDefault(arr[i], 0) - 1;
            if (value2 == 0) {
                map.remove(arr[i]);
            } else {
                map.put(arr[i], value2);
            }
            if (map.isEmpty()) { // map is empty means all elements within the current subarray of arr and sorted are identical
                result++;
            }
        }
        return result;
    }
}

// 方法2：单调栈
// 遍历arr，并维护一个单调递增的单调栈
// 对于一个chunk，这个chunk中的最大值应该小于这个chunk之后的所有数字
// 所以可以维护一个单调递增的单调栈。如果当前数字arr[i]大于等于栈顶元素，则可以作为一个新的chunk，push到栈顶
// 如果arr[i]小于栈顶元素，说明需要把当前arr[i]合并到上一个chunk中。循环从栈中pop，直到栈为空或栈顶元素小于等于arr[i]为止
// 用curMax记录新的合并后的chunk的最大元素的值。在循环pop结束后，将这个新的最大值push回栈中
// 由于栈中元素单调递增，所以实际上curMax就等于第一次pop出来的元素值

class Solution {
    public int maxChunksToSorted(int[] arr) {
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            int cur = arr[i];
            int curMax = arr[i];
            while (!stack.isEmpty() && stack.peek() > cur) {
                curMax = Math.max(curMax, stack.pop());
            }
            stack.push(curMax);
        }
        return stack.size();
    }
}