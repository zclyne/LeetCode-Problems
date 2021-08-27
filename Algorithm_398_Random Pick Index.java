import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

// 方法1：用map存储下nums中的每个num与其对应的所有下标的映射
// 在调用pick时，先从map中取出target对应的下标列表idxList
// 然后在[0, idxList.size() - 1]范围内随机生成一个int，作为要选取的下标randIdx
// 最后返回idxList.get(randIdx)即可

class Solution {

    private Random random;
    private Map<Integer, List<Integer>> map = new HashMap<>();

    public Solution(int[] nums) {
        random = new Random();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            List<Integer> idxList = map.getOrDefault(num, new ArrayList<>());
            idxList.add(i);
            map.put(num, idxList);
        }
    }
    
    public int pick(int target) {
        List<Integer> idxList = map.get(target);
        int randIdx = random.nextInt(idxList.size());
        return idxList.get(randIdx);
    }
}

// 方法2：蓄水池抽样
// 假设数据以流的方式输入。当前正要读取第n个数据，则我们以1 / n的概率留下该数据，否则留下前n - 1个数据中的一个
// 以这种方法来选择，最终数据流中所有数据被选择到的概率是相同的
// https://leetcode-cn.com/problems/random-pick-index/solution/xu-shui-chi-chou-yang-wen-ti-by-an-xin-9/

class Solution {
    private int[] nums;
    public Solution(int[] nums) {
       this.nums = nums;
    }
    
    public int pick(int target) {
        Random r = new Random();
        int n = 0;
        int index = 0;
        for(int i = 0;i < nums.length;i++)
            if(nums[i] == target){
                n++;
                //以1/n的概率留下该数据
                if(r.nextInt() % n == 0) index = i;
            }
        return index;
    }
}