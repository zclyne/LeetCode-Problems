/**
 * // This is Sea's API interface.
 * // You should not implement it, or speculate about its implementation
 * class Sea {
 *     public boolean hasShips(int[] topRight, int[] bottomLeft);
 * }
 */

// 方法：分成左上、右上、左下、右下四个部分，递归查找
// 类似于二维的二分查找

class Solution {
    public int countShips(Sea sea, int[] topRight, int[] bottomLeft) {
        if (!sea.hasShips(topRight, bottomLeft)) {
            return 0;
        }
        if (topRight[0] == bottomLeft[0] && topRight[1] == bottomLeft[1]) {
            return 1;
        }
        int leftX = bottomLeft[0], rightX = topRight[0];
        int upperY = topRight[1], bottomY = bottomLeft[1];
        int midX = (leftX + rightX) / 2;
        int midY = (upperY + bottomY) / 2;
        return countShips(sea, new int[]{midX, upperY}, new int[]{leftX, midY + 1})
             + countShips(sea, new int[]{rightX, upperY}, new int[]{midX + 1, midY + 1})
             + countShips(sea, new int[]{midX, midY}, new int[]{leftX, bottomY})
             + countShips(sea, new int[]{rightX, midY}, new int[]{midX + 1, bottomY});
    }
}