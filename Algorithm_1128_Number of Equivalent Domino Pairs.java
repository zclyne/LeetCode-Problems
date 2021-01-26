import java.util.HashMap;
import java.util.Map;

// 思路：用map存储每个domino出现的次数，对于一个domino，如果domino[0] > domino[1]
// 就将它们互换，从而确保domino[0] <= domino[1]
// 为了让map能够存储数组，使用Arrays.toString()来将数组转换成字符串
// 对于新发现的每一个domino，它都能和之前的domino形成equivalent domino对
// 所以result += count

// 优化：条件中指出1 <= dominoes[i][j] <= 9
// 因此一个domino可以用唯一的一个两位整数来表示，即10 * dominoes[i][0] + dominoes[i][1]
// 这样就可以用一个长度为100的整数数组来存储出现的次数，从而优化时间和空间

class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        int result = 0;
        Map<String, Integer> map = new HashMap<>();
        for (int[] domino : dominoes) {
            // make sure domino[0] <= domino[1]
            if (domino[0] > domino[1]) {
                swap(domino, 0, 1);
            }
            String s = Arrays.toString(domino);
            int count = map.getOrDefault(s, 0);
            result += count;
            map.put(s, count + 1);
        }
        return result;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}