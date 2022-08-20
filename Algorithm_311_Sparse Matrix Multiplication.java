import java.util.*;

// https://leetcode.com/problems/sparse-matrix-multiplication/discuss/76154/Easiest-JAVA-solution
// 因为输入的矩阵是sparse的，大部分元素都是0，所以先遍历mat1和mat2
// 预先记录下mat1每一行中非0元素的列号，以及mat2每一列中非0元素的行号
// 然后按照矩阵乘法规则，mat1的每一行与mat2的每一列相乘
// 只需要考虑非0的元素即可

class Solution {
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        int p = mat1.length, q = mat1[0].length, r = mat2[0].length;
        int[][] result = new int[p][r];

        // get the indexes of all non-zero elements in each row of mat1
        List<List<Integer>> idxs1 = new ArrayList<>();
        for (int i = 0; i < p; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < q; j++) {
                if (mat1[i][j] != 0) {
                    temp.add(j);
                }
            }
            idxs1.add(temp);
        }

        // get the indexes of all non-zero elements in each column of mat2
        List<List<Integer>> idxs2 = new ArrayList<>();
        for (int j = 0; j < r; j++) {
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < q; i++) {
                if (mat2[i][j] != 0) {
                    temp.add(i);
                }
            }
            idxs2.add(temp);
        }

        // calculate each element of the result
        for (int i = 0; i < p; i++) {
            List<Integer> row = idxs1.get(i);
            for (int j = 0; j < r; j++) {
                List<Integer> col = idxs2.get(j);
                int x = 0, y = 0;
                int cur = 0;
                while (x < row.size() && y < col.size()) {
                    int pos1 = row.get(x), pos2 = col.get(y);
                    if (pos1 == pos2) {
                        cur += mat1[i][pos1] * mat2[pos2][j];
                        x++;
                        y++;
                    } else if (pos1 < pos2) {
                        x++;
                    } else {
                        y++;
                    }
                }
                result[i][j] = cur;
            }
        }

        return result;
    }
}