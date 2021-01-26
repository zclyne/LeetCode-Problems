import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 思路：直接逐行构建pascal triangle
// 从已有的一行构建下一行时，要从后向前遍历，只有这样
// 才能保证上一行的数字不会被下一行覆盖

class Solution {
    public List<Integer> getRow(int rowIndex) {
        int[] result = new int[rowIndex + 1];
        result[0] = 1;
        for (int i = 1; i <= rowIndex; i++) {
            int endIndex = i;
            // traverse from end to start so that the original numbers in result
            // won't be changed
            for (int j = endIndex; j >= 0; j--) {
                if (j == endIndex || j == 0) {
                    result[j] = 1;
                } else {
                    result[j] = result[j] + result[j - 1];
                }
            }
        }
        List<Integer> resultList = new ArrayList<>();
        for (int num : result) {
            resultList.add(num);
        }
        return resultList;
    }
}