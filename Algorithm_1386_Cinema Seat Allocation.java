import java.util.*;

// 先遍历reservedSeats数组，找到有座位被预订了的所有行，放入hashmap中
// map的值是一个长度为11的boolean数组，表示哪个位置被预订
// 对于每一个这样的行，用函数getFourGroupNumberFitsInRow得到所能容纳的4人group的数量
// 如果2~9都没有被预订，则可以容纳2个group
// 否则，若2~5或4~7或6~9没有被预定，则可以容纳1个group
// 否则，无法容纳四人group，返回0
// 对于没有座位被预定的行，必然可以放下2个四人group
// boolean数组可以优化为一个int，用每个bit来表示某个座位是否被使用

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, boolean[]> map = new HashMap<>();
        for (int[] seat : reservedSeats) {
            int row = seat[0], label = seat[1];
            if (!map.containsKey(row)) {
                map.put(row, new boolean[11]);
            }
            map.get(row)[label] = true;
        }

        int numWholeRows = n - map.size();
        int result = 2 * numWholeRows;

        for (boolean[] rowSeats : map.values()) {
            result += this.getFourGroupNumberFitsInRow(rowSeats);
        }

        return result;
    }

    private int getFourGroupNumberFitsInRow(boolean[] rowSeats) {
        boolean available23 = !rowSeats[2] && !rowSeats[3];
        boolean available45 = !rowSeats[4] && !rowSeats[5];
        boolean available67 = !rowSeats[6] && !rowSeats[7];
        boolean available89 = !rowSeats[8] && !rowSeats[9];

        if (available23 && available45 && available67 && available89) {
            return 2;
        } else if (available45 && available67 || available23 && available45 || available67 && available89) {
            return 1;
        }
        return 0;
    }
}

// 用integer代替boolean数组的优化解法

class Solution2 {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> graph = new HashMap<>();
        for (int[] reserved : reservedSeats) {
            int row = reserved[0];
            int col = reserved[1];
            graph.put(row, graph.getOrDefault(row, 0) | (1 << col)); // create a bit vector of reserved seats
        }
        int max = 0;
        for (int row : graph.keySet()) {
            int reserved = graph.get(row);
            int cnt = 0;
            if ((reserved &  60) == 0) cnt += 1; // check if seats 2,3,4,5 are available
            if ((reserved & 960) == 0) cnt += 1; // check if seats 6,7,8,9 are available
            if ((reserved & 240) == 0 && cnt == 0) cnt = 1; // check if seats 4,5,6,7 are available
            max += cnt;
        }
        return max + 2 * (n - graph.size());
    }
}