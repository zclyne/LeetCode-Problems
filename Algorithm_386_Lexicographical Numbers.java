import java.util.List;
import java.util.Queue;

class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> list = new ArrayList<>(n);
        int curr = 1;
        for (int i = 1; i <= n; i++) {
            list.add(curr);
            if (curr * 10 <= n) curr *= 10;
            else if (curr % 10 != 9 && curr + 1 <= n) curr++;
            else // curr % 10 == 9 || curr == n
            {
                curr /= 10;
                while (curr % 10 == 9) curr /= 10;
                curr++;
            }
        }
        return list;
    }
}

// dfs solution
public class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        for(int i=1;i<10;++i){
          dfs(i, n, res); 
        }
        return res;
    }
    
    public void dfs(int cur, int n, List<Integer> res){
        if(cur>n)
            return;
        else{
            res.add(cur);
            for(int i=0;i<10;++i){
                if(10*cur+i>n)
                    return;
                dfs(10*cur+i, n, res);
            }
        }
    }
}

// Time Limit Exceeded Solution
class Solution {
    public List<Integer> lexicalOrder(int n) {
        Queue<String> pq = new PriorityQueue<String>();
        for (int i = 1; i <= n; i++) pq.offer(String.valueOf(i)); // String.valueOf(int num) converts num from int to String, we can also use Integer.toString(int num)
        List<Integer> res = new ArrayList<Integer>();
        while (!pq.isEmpty())
        {
            String s = pq.poll();
            res.add(Integer.parseInt(s)); // Integer.parseInt(String s) converts s from String to int
        }
        return res;
    }
}