// 思路：用getAvg函数计算坐标为[i][j]的像素经过smooth后的值，并将该值赋予res[i][j]

class Solution {
    public int[][] imageSmoother(int[][] M) {
        int[][] res = new int[M.length][M[0].length];
        for (int i = 0; i < M.length; i++)
            for (int j = 0; j < M[0].length; j++)
                res[i][j] = getAvg(M, i, j);
        return res;
    }
    int getAvg(int[][] M, int i, int j)
    {
        int sum = M[i][j];
        int count = 1;
        if (i > 0)
        {
            sum += M[i - 1][j];
            count++;
            if (j > 0)
            {
                sum += M[i - 1][j - 1];
                count++;
            }
            if (j < M[0].length - 1)
            {
                sum += M[i - 1][j + 1];
                count++;
            }
        }
        if (i < M.length - 1)
        {
            sum += M[i + 1][j];
            count++;
            if (j > 0)
            {
                sum += M[i + 1][j - 1];
                count++;
            }
            if (j < M[0].length - 1)
            {
                sum += M[i + 1][j + 1];
                count++;
            }
        }
        if (j > 0)
        {
            sum += M[i][j - 1];
            count++;
        }
        if (j < M[0].length - 1)
        {
            sum += M[i][j + 1];
            count++;
        }
        return sum / count;
    }
}