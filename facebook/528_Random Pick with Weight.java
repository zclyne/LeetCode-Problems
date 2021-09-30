import java.util.TreeMap;
import java.util.Map;
import java.util.Random;

// 方法1：对weight求前缀和，并将前缀和到index的映射存储在treeMap中
// pickIndex()时，随机生成一个范围在1 ~ totalWeight之间的随机数
// 然后在treemap中找到key大于等于这个随机数的最小的entry，其value就是对应的index

class Solution {
    private int totalWeight;
    private TreeMap<Integer, Integer> weightToIndex;
    private Random random;

    public Solution(int[] w) {
        int n = w.length;
        weightToIndex = new TreeMap<>();
        this.random = new Random();
        
        int curWeight = 0;
        for (int i = 0; i < n; i++) {
            curWeight += w[i];
            weightToIndex.put(curWeight, i);
        }

        this.totalWeight = curWeight;
    }
    
    public int pickIndex() {
        int weight = 1 + random.nextInt(this.totalWeight);
        Map.Entry<Integer, Integer> entry = this.weightToIndex.ceilingEntry(weight);
        return entry.getValue();
    }
}

// 方法2：前缀和数组+二分查找

class Solution {

    Random random;
    int[] wSums;
    
    public Solution(int[] w) {
        this.random = new Random();
        for(int i=1; i<w.length; ++i)
            w[i] += w[i-1];
        this.wSums = w;
    }
    
    public int pickIndex() {
        int len = wSums.length;
        int idx = random.nextInt(wSums[len-1]) + 1;
        int left = 0, right = len - 1;
        // search position 
        while(left < right){
            int mid = left + (right-left)/2;
            if(wSums[mid] == idx)
                return mid;
            else if(wSums[mid] < idx)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */