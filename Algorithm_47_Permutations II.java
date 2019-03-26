import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

// 思路：用数组visited记录已经使用过的数字。先对nums排序，方便之后判断重复元素
// 为避免重复，在helper的循环中，若满足nums[i - 1] == nums[i] && !visited[i - 1]，就不进入递归。
// 这一条件保证了最终curList中出现的相同数字的顺序一定等于该数字在排序后的nums中的顺序
// 因为只有当始终保证visited[i - 1] == true时，才会进入下一层的递归
// 例如，输入为[1, 1, 2]，记为[1(1), 1(2), 2]
// 则对于其中一种排序[1, 2, 1]，有两种情况：[1(1), 2, 1(2)]或[1(2), 2, 1(1)]
// 而由于判断了visited[i - 1]，所以只有[1(1), 2, 1(2)]是成立的，而第二种情况下不会进入递归

class Solution {

    private List<List<Integer>> res = new ArrayList<>();
    private boolean[] visited;

    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        visited = new boolean[nums.length];
        helper(new ArrayList<Integer> (), nums);
        return res;
    }

    private void helper(List<Integer> curList, int[] nums) {
        if (curList.size() == nums.length) {
            res.add(new ArrayList<>(curList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] || i > 0 && nums[i - 1] == nums[i] && !visited[i - 1]) continue;
            visited[i] = true;
            curList.add(nums[i]);
            helper(curList, nums);
            visited[i] = false;
            curList.remove(curList.size() - 1);
        }
    }

}