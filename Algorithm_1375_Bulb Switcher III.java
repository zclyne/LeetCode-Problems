// 方法：变量right记录当前亮着的编号最大的灯泡
// 根据题意，在第i天时，若灯泡1 ~ i都亮着，就说明所有灯泡都是蓝色（i从1开始）
// 则result++

class Solution {
    public int numTimesAllBlue(int[] light) {
        int right = 0, result = 0;
        for (int i = 0; i < light.length; i++) {
            right = Math.max(right, light[i]);
            if (right == i + 1) {
                result++;
            }
        }
        return result;
    }
}