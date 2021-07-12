// 方法：扫描线法
// https://www.youtube.com/watch?v=8Kd-Tn_Rz7s

class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<int[]> heights = new ArrayList<>();
        for (int[] b: buildings) {
            // 大楼的左端点，进入事件，用高度的负值来表示
            heights.add(new int[]{b[0], -b[2]});
            // 大楼的右端点，离开事件，用高度的正值来表示
            heights.add(new int[]{b[1], b[2]});
        }

        // 按照x轴坐标从小到大排序。如果x轴坐标相等，按照高度从低到高排序
        // 在两个进入事件x轴相同时，需要优先处理高度最高的大楼，由于进入事件的高度是负值，所以排序后会排在前面
        // 在两个离开事件x轴相同时，需要优先处理高度最低的大楼。同理，高度低的楼在排序后排在前面
        // 若进入事件和离开事件的x轴相同，需要优先处理进入事件，同样能够满足
        Collections.sort(heights, (a, b) -> (a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]);

        // 按照从大到小的顺序排序，key为大楼的高度，value为这个高度的楼的数量
        TreeMap<Integer, Integer> heightMap = new TreeMap<>(Collections.reverseOrder());
        heightMap.put(0, 1); // 高度为0，代表地平线
        int prevHeight = 0; // 记录上一个key point的高度

        List<List<Integer>> skyLine = new LinkedList<>();
        
        for (int[] h: heights) {
            if (h[1] < 0) { // 进入事件
                Integer cnt = heightMap.get(-h[1]);
                cnt = ( cnt == null ) ? 1 : cnt + 1;
                heightMap.put(-h[1], cnt);
            } else { // 离开事件
                Integer cnt = heightMap.get(h[1]);
                if (cnt == 1) { // 离开后，已经没有大楼的高度为h[1]，从map中删除
                    heightMap.remove(h[1]);
                } else {
                    heightMap.put(h[1], cnt - 1);
                }
            }
            // 获取事件完成后最高的楼的高度。如果没有大楼，则返回的是地平线的高度0
            int currHeight = heightMap.firstKey();
            // 如果是多个进入事件x轴相同，或多个离开事件的y轴相同，则对除了第一个以外的其他事件，都有prevHeight == currHeight
            // 不会向skyLine中添加内容
            if (prevHeight != currHeight) {
                skyLine.add(Arrays.asList(h[0], currHeight));
                prevHeight = currHeight;
            }
        }
        return skyLine;
    }
}