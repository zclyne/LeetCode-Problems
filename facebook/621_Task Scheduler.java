// https://leetcode-cn.com/problems/task-scheduler/solution/ren-wu-diao-du-qi-by-leetcode-solution-ur9w/

class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] c = new int[26];
        for(char t : tasks){
            c[t - 'A']++;
        }
        Arrays.sort(c);
        int i = 25;
        while(i >= 0 && c[i] == c[25]) i--; // 排序完后，c[25]中即为频率最高的task的出现次数，此while循环用于计算具有最高频率的task的个数

        return Math.max(tasks.length, (c[25] - 1) * (n + 1) + 25 - i); // (c[25] - 1)*(n + 1)是前k - 1个chunk占用的interval个数，25-i是最后一个chunk占用的interval个数
        // 若前k - 1个chunk不足以容纳所有的task，则结果一定为tasks.length
    }
}