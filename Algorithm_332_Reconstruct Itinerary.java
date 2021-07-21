import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Map;

// 方法1：首先遍历tickets，得到所有的机场名
// 然后将机场名列表按字典序排序，然后建立机场名和整数下标的映射
// 再构建图graph。这里要注意可能有多张机票的from和to都相同，所以不能用boolean数组，必须用int数组来表示图
// 这表示两个节点之间可能有多条有向边
// 最后从JFK为起点进行dfs，按照下标从小到大遍历。由于机场名已经排序过，所以下标从小到大就自然对应了机场名字典序从小到大排序
// 所以dfs所找到的第一个可行的path就是字典序最小的path

class Solution {

    private Map<String, Integer> airportToIndex;
    private Map<Integer, String> indexToAirport;
    private int n = 0;

    public List<String> findItinerary(List<List<String>> tickets) {
        // get all airports
        Set<String> airports = new HashSet<>();
        for (List<String> ticket : tickets) {
            String from = ticket.get(0), to = ticket.get(1);
            if (!airports.contains(from)) {
                airports.add(from);
            }
            if (!airports.contains(to)) {
                airports.add(to);
            }
        }

        // sort airports according to lexicographical order
        List<String> airportList = new ArrayList<>(airports);
        Collections.sort(airportList);

        // arrange indicies to airports
        airportToIndex = new HashMap<>();
        indexToAirport = new HashMap<>();
        for (String airport : airportList) {
            airportToIndex.put(airport, n);
            indexToAirport.put(n, airport);
            n++;
        }

        // build the graph
        int[][] graph = new int[n][n];
        for (List<String> ticket : tickets) {
            String from = ticket.get(0), to = ticket.get(1);
            int fromIdx = airportToIndex.get(from), toIdx = airportToIndex.get(to);
            graph[fromIdx][toIdx]++;
        }

        // the itinerary must begin with JFK
        int jfkIndex = airportToIndex.get("JFK");
        List<String> path = new ArrayList<>();
        path.add("JFK");
        return dfs(graph, jfkIndex, 0, tickets.size(), path);
    }

    private List<String> dfs(int[][] graph, int curIdx, int usedTickets, int totalTickets, List<String> path) {
        if (usedTickets == totalTickets) { // all tickets have been used
            return new ArrayList<>(path);
        }

        int[] neighbors = graph[curIdx];
        for (int i = 0; i < n; i++) {
            if (neighbors[i] > 0) { // can fly from curIdx to i
                neighbors[i]--; // the ticket is used
                path.add(indexToAirport.get(i));
                List<String> nextPath = dfs(graph, i, usedTickets + 1, totalTickets, path);
                neighbors[i]++;
                path.remove(path.size() - 1);
                if (nextPath != null) { // found a valid path
                    return nextPath;
                }
            }
        }

        return null;
    }

}

// 方法2：本质上就是找欧拉通路，Hierholzer算法
// Hierholzer算法用于在连通图中寻找欧拉路径
// 1. 从起点出发，进行dfs
// 2. 每次沿着某条边从某个顶点移动到另一个顶点的时候，都需要删除这条边
// 3. 如果没有可移动的路径，则将所在节点加入到栈中，并返回
// 用PriorityQueue来保证每次取到的下一个节点都是字典序最小的
// 当我们遍历完一个节点所连的所有节点后，我们才将该节点入栈（即逆序入栈）
// 对于当前节点而言，从它的每一个非「死胡同」分支出发进行深度优先搜索，都将会搜回到当前节点
// 而从它的「死胡同」分支出发进行深度优先搜索将不会搜回到当前节点。也就是说当前节点的死胡同分支将会优先于其他非「死胡同」分支入栈
// https://leetcode-cn.com/problems/reconstruct-itinerary/solution/zhong-xin-an-pai-xing-cheng-by-leetcode-solution/

class Solution {
    Map<String, PriorityQueue<String>> map = new HashMap<String, PriorityQueue<String>>();
    List<String> itinerary = new LinkedList<String>();

    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            String src = ticket.get(0), dst = ticket.get(1);
            if (!map.containsKey(src)) {
                map.put(src, new PriorityQueue<String>());
            }
            map.get(src).offer(dst);
        }
        dfs("JFK");
        Collections.reverse(itinerary);
        return itinerary;
    }

    public void dfs(String curr) {
        while (map.containsKey(curr) && map.get(curr).size() > 0) {
            String tmp = map.get(curr).poll();
            dfs(tmp);
        }
        itinerary.add(curr);
    }
}