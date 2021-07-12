import java.util.ArrayList;

// 解法1：枚举时分
// 对于小时和分钟的所有取值进行枚举，计算二者的二进制表示的1的个数之和
// 若等于turnedOn，说明是答案之一，添加到数组ans中
class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> ans = new ArrayList<String>();
        for (int h = 0; h < 12; ++h) {
            for (int m = 0; m < 60; ++m) {
                if (Integer.bitCount(h) + Integer.bitCount(m) == turnedOn) {
                    ans.add(h + ":" + (m < 10 ? "0" : "") + m);
                }
            }
        }
        return ans;
    }
}

// 解法2：二进制枚举
// 枚举所有2^10=1024种灯的开闭组合，其高4位是时，低6位是分
class Solution2 {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> ans = new ArrayList<String>();
        for (int i = 0; i < 1024; ++i) {
            int h = i >> 6, m = i & 63; // 用位运算取出高 4 位和低 6 位
            if (h < 12 && m < 60 && Integer.bitCount(i) == turnedOn) {
                ans.add(h + ":" + (m < 10 ? "0" : "") + m);
            }
        }
        return ans;
    }
}

// 解法3：递归回溯
class RecursiveSolution {
    List<String> ans = new ArrayList<>();
    int[] a = new int[]{1,2,4,8,1,2,4,8,16,32}; // 前4位是时，后6位是分
    
    public List<String> readBinaryWatch(int num) {
        dfs(num, 0, 0, 0);
        return ans;
    }

    // cnt记录剩余的1的个数，h记录当前的时，m记录当前的分，idx是当前的下标
    void dfs(int cnt,int h,int m,int idx){
        if(cnt == 0) {
            ans.add(h + ":" + ( m > 9 ? m : "0" + m));
        }
        for(int i = idx; i < 10 && cnt > 0; i++){
            if(i < 4 && h + a[i] < 12) {
                dfs(cnt - 1, h + a[i], m, i + 1);
            }
            if(i >= 4 && m + a[i] < 60) {
                dfs(cnt - 1,h , m + a[i], i + 1);
            }
        }
    }
}