// 思路：双指针。posToInsert指向下一个要插入的index
// 对于当前的元素nums[cur]，如果是第一次出现，则插入到nums[posToInsert]上，并且posToInsert++
// 并且置repeated = false，表示当前元素还未重复过
// 如果nums[cur] == nums[cur - 1]，说明当前元素并非第一次出现
// 此时，如果repeated == false，说明之前还未重复使用过这个元素
// 因此依然可以把它插入到nums[posToInsert]位置上，并且posToInsert++
// 否则，不做任何处理

class Solution {
    public int removeDuplicates(int[] nums) {
        int posToInsert = 1, cur = 1;
        boolean repeated = false;
        for (cur = 1; cur < nums.length; cur++) {
            if (nums[cur] == nums[cur - 1]) {
                if (!repeated) {
                    nums[posToInsert++] = nums[cur];
                    repeated = true;
                }
            } else {
                nums[posToInsert++] = nums[cur];
                repeated = false;
            }
        }
        return posToInsert;
    }
}