import java.util.List;
import java.util.ArrayList;

// 思路：回溯法。用数组visited记录下当前已经使用过的所有数字，curRes按顺序存放当前使用的所有数字
// 当curRes.size() == nums.length时，表明所有数字都已经被使用，此时的curRes就是一个答案，添加到res中
// 对于仍未使用的数字，将其visited置为true，并在curRes末尾添加上该数字后，递归调用backtracking()
// 注意递归调用完成后，要将visited置为false，并从curRes结尾删除该数字

class Solution {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        backtracking(nums, new boolean[nums.length], new ArrayList<>());
        return res;
    }

    private void backtracking(int[] nums, boolean[] visited, List<Integer> curRes) {
        if (curRes.size() == nums.length) { // finish
            res.add(new ArrayList<>(curRes));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                curRes.add(nums[i]);
                backtracking(nums, visited, curRes);
                curRes.remove(curRes.size() - 1);
                visited[i] = false;
            }
        }
    }

}