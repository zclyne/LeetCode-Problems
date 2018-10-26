// 思路：需要考虑三种情况：一是数组的起始为一串0，其长度为startLen，此时Alex应该坐在seats[0]位置上，与最近的人距离为startLen
// 二是数组的末尾有一串0，其长度为endLen，此时Alex应该坐在seats[seats.length - 1]位置上，与最近的人距离为endLen
// 三是坐在start到end之间的某一串0的最中间，此时问题转化为找这个子数组中连续的0的最大长度midMaxLen
// 若该最大长度是偶数，与最近的人的距离则是midMaxLen / 2，若是奇数则与最近的人的距离是miMaxLen / 2 + 1

class Solution {
    public int maxDistToClosest(int[] seats) {
        int start = 0, startLen, end = seats.length - 1, endLen, midMaxLen = 0;
        while (seats[start] == 0) start++;
        startLen = start;
        while (seats[end] == 0) end--;
        endLen = seats.length - 1 - end;
        for (int i = start; i < end; i++) {
            if (seats[i] == 1) start = i + 1;
            else if (i - start + 1 > midMaxLen) midMaxLen = i - start + 1;
        }
        return Math.max(startLen, Math.max(endLen, midMaxLen % 2 == 0 ? midMaxLen / 2  : midMaxLen / 2 + 1));
    }
}