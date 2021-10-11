import java.util.ArrayList;

class Solution {

    private List<List<Integer>> result;

    public List<List<Integer>> subsets(int[] nums) {
        result = new ArrayList<>();
        backtracking(nums, 0, new ArrayList<>());
        return result;
    }

    private void backtracking(int[] nums, int curPos, List<Integer> curSet) {
        if (curPos == nums.length) {
            result.add(new ArrayList<>(curSet));
            return;
        }
        // don't add cur number
        backtracking(nums, curPos + 1, curSet);
        // add cur number
        curSet.add(nums[curPos]);
        backtracking(nums, curPos + 1, curSet);
        curSet.remove(curSet.size() - 1);
    }
}