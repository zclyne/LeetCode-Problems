// 方法：计算重叠部分的x1, x2, y1, y2坐标
// 若x1 >= x2或y1 >= y2，说明没有重叠，重叠部分面积为0
// 用两矩形面积相加后再减去重叠部分面积即为答案

class Solution {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int areaA = (ax2 - ax1) * (ay2 - ay1);
        int areaB = (bx2 - bx1) * (by2 - by1);
        int x1 = Math.max(ax1, bx1), x2 = Math.min(ax2, bx2), y1 = Math.max(ay1, by1), y2 = Math.min(ay2, by2);
        int areaOverlap = 0;
        if (x1 < x2 && y1 < y2) { // has overlap
            areaOverlap = (x2 - x1) * (y2 - y1);
        }
        return areaA + areaB - areaOverlap;
    }
}