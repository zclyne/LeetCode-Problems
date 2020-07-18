import java.util.HashMap;
import java.util.Map;

// 思路：一条直线可以由方程y = kx + b来表示。题目中由于所有点的坐标都是整数
// 因此k必然是有理数，所以k可以由k = y0 / x0来表示，其中y0、x0是整数
// 这里采用嵌套循环，对于points中的任意两个点，可以通过点坐标来计算出x和y坐标的差值
// 并计算这两个差值的最大公约数，并将两个差值分别除以这个最大公约数，就能够得到梯度的分式形式
// 用这个分式形式的字符串作为键，并以在该直线上除该点自身以外的点的数量为值构建PointCountMap
// 还要注意可能有多个点的坐标相同的情况，这种情况由变量duplicate来记录
// 注意外层循环的结束条件必须是i == points.length - 1，因为points中有可能只包含一个点

class Solution {
    public int maxPoints(int[][] points) {
        Map<String, Integer> pointCountMap = new HashMap<>();
        int result = 0;
        // calculate line equation
        for (int i = 0; i < points.length; i++) {
            pointCountMap.clear();
            int duplicate = 0, max = 0;
            for (int j = i + 1; j < points.length; j++) {
                int deltaX = points[i][0] - points[j][0];
                int deltaY = points[i][1] - points[j][1];
                // i and j are located at the same position
                if (deltaX == 0 && deltaY == 0) {
                    duplicate++;
                    continue;
                }
                // calculate greatest common divisor and divide deltaX and deltaY by it
                // in order to get the gradient of this line
                int gcd = generateGCD(deltaX, deltaY);
                if (gcd != 0) {
                    deltaX /= gcd;
                    deltaY /= gcd;
                }
                String gradient = deltaY + "/" + deltaX;
                pointCountMap.put(gradient, pointCountMap.getOrDefault(gradient, 0) + 1);
                max = Math.max(max, pointCountMap.get(gradient));
            }
            result = Math.max(result, max + duplicate + 1); // +1 for the point itself
        }
        return result;
    }

    private int generateGCD(int a, int b){
        if (b == 0) {
            return a;
        }
        else {
            return generateGCD(b, a % b);
        }
    }
}