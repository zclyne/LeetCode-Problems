import java.util.Random;
import java.util.TreeMap;

// 思路：根据矩形的面积不同来设定随机选取时的权值
// 用TreeMap来保存累计的面积之和与下标的关系，在pick时，随机选取一个面积
// 从map中查到该面积对应的矩形的下标，再从矩形中随机选取点的x和y轴坐标
// 注意计算面积时，长和宽都要加1，这是由于矩形边缘上的点也算作矩形覆盖的区域
// 例如一个边长为1的正方形，其实有2x2=4个顶点都可以作为备选

class Solution {

    private TreeMap<Integer, Integer> map = new TreeMap<>();
    private int sumArea;
    private int[][] rects;

    public Solution(int[][] rects) {
        for (int i = 0; i < rects.length; i++) {
            int len = rects[i][2] - rects[i][0] + 1, wid = rects[i][3] - rects[i][1] + 1;
            sumArea += len * wid;
            map.put(sumArea, i);
        }
        this.rects = rects;
    }
    
    public int[] pick() {
        Random r = new Random();
        int area = r.nextInt(sumArea + 1);
        int selectedIdx = map.get(map.ceilingKey(area));
        int leftBottomX = rects[selectedIdx][0], leftBottomY = rects[selectedIdx][1];
        int rightTopX = rects[selectedIdx][2], rightTopY = rects[selectedIdx][3];
        int randX = leftBottomX + r.nextInt(rightTopX - leftBottomX + 1);
        int randY = leftBottomY + r.nextInt(rightTopY - leftBottomY + 1);
        return new int[] {randX, randY};
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(rects);
 * int[] param_1 = obj.pick();
 */