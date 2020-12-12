import java.util.ArrayList;
import java.util.Arrays;

// 思路：考虑所有人中身高最矮的那些人。他们在队列中不会影响任何其他人的k值，因此先排好其他人，最后再把最矮的这些人插入到队列中
// 所要插入的位置的index就是最矮的这些人每个人的k值。因为已经排好的队列中每个人都比他们高
// 对于较高的那些人，用同样的想法处理
// 因此要先按照k从小到大把身高最高的人全部排好，然后把身高第二高的那些人按照各自的k值插入到队列中，依此类推
// 初始时要对数组people做排序，其排序规则为：若两人身高不相等，则按身高从高到低排列；若两人身高相等，则按照k从小到大排列

class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (int[] p1, int[] p2) -> 
            {
                if (p1[0] != p2[0]) {
                    return p2[0] - p1[0]; // sort by height from taller to shorter
                }
                return p1[1] - p2[1]; // if they have the same height, sort by number of taller people front of them
            });
        ArrayList<int[]> resList = new ArrayList<>();
        for (int[] p : people) {
            resList.add(p[1], p);
        }
        return resList.toArray(new int[resList.size()][2]);
    }
}