import java.util.HashSet;
import java.util.Set;

// 方法：遍历rectangles，记录对应的完美矩形的四个顶点的坐标
// 如果rectangles中的所有矩形最终能够拼接成一个完美矩形，那么必然要求完美矩形的面积等于所有rectangles中的矩形面积之和
// 在遍历rectangles的过程中，用变量totalArea来记录所有矩形的面积之和
// 如果最终面积不相等，则返回false
// 然而仅通过面积相等是无法保证能够拼接出完美矩形的，因为两个矩形有可能重叠
// 因此还需要对矩形的顶点进行判断。如果能够拼接成完美矩形，那么除了最终的完美矩形的四个顶点只出现一次以外
// 其他顶点都会出现2次或4次
// 用一个hashset pointSet来存储顶点的出现情况。如果一个顶点不存在于pointSet中，则将其加入
// 如果已经存在，则将其从pointSet中删除
// 最终，pointSet中的size应该为4，并且4个顶点恰好分别为完美矩形的四个顶点
// 否则，返回false
// https://leetcode-cn.com/problems/perfect-rectangle/solution/java-cong-dian-dao-mian-li-jie-wan-mei-ju-xing-wen/

class Solution {
    public boolean isRectangleCover(int[][] rectangles) {
        // (x1, y1) and (x2, y2) is the bottom-left point and the top-right point of the perfect rectangle respectively
        int x1 = Integer.MAX_VALUE, y1 = Integer.MAX_VALUE, x2 = Integer.MIN_VALUE, y2 = Integer.MIN_VALUE;
        int totalArea = 0;
        Set<String> pointSet = new HashSet<>();

        for (int[] rectangle : rectangles) {
            // update the coordinate of the 4 points of the perfect rectangle
            x1 = Math.min(x1, rectangle[0]);
            y1 = Math.min(y1, rectangle[1]);
            x2 = Math.max(x2, rectangle[2]);
            y2 = Math.max(y2, rectangle[3]);

            // add the area of the current rectangle to the total area
            int area = (rectangle[2] - rectangle[0]) * (rectangle[3] - rectangle[1]);
            totalArea += area;

            // add the four points of the current rectangle to the set if not exist
            // if already exists, remove the point
            String bottomLeftPoint = rectangle[0] + ", " + rectangle[1];
            String bottomRightPoint = rectangle[2] + ", " + rectangle[1];
            String topLeftPoint = rectangle[0] + ", " + rectangle[3];
            String topRightPoint = rectangle[2] + ", " + rectangle[3];
            String[] points = new String[]{bottomLeftPoint, bottomRightPoint, topLeftPoint, topRightPoint};
            for (String point : points) {
                if (pointSet.contains(point)) {
                    pointSet.remove(point);
                } else {
                    pointSet.add(point);
                }
            }
        }

        int perfectRectangleArea = (x2 - x1) * (y2 - y1);

        // area doesn't match
        if (totalArea != perfectRectangleArea) {
            return false;
        }

        // points don't match
        if (pointSet.size() != 4 || !pointSet.contains(x1 + ", " + y1) || !pointSet.contains(x1 + ", " + y2)
            || !pointSet.contains(x2 + ", " + y1) || !pointSet.contains(x2 + ", " + y2)) {
            return false;
        }

        return true;
    }
}