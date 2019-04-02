import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

// 思路：先对nums从小到大排序，然后开始递归，cur记录当前所选的所有元素，curStart记录循环变量的起始位置
// 在DFS的起始时，先把当前的cur加入到res中
// 循环变量i从curStart开始直到nums.length - 1
// 将nums[i]加入cur，递归调用DFS，新的起始位置为i + 1
// 由于nums已经有序，因此设置新的起始位置为i + 1即可避免产生重复

class Solution {

    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        DFS(nums, new ArrayList<Integer>(), 0);
        return res;
    }

    public void DFS(int[] nums, List<Integer> cur, int curStart) {
        res.add(new ArrayList<Integer>(cur));
        for (int i = curStart; i < nums.length; i++) {
            cur.add(nums[i]);
            DFS(nums, cur, i + 1);
            cur.remove(cur.size() - 1);
        }
    }

}