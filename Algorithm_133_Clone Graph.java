import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

// 解法1：DFS
// 思路：DFS，用map来记录已经克隆过的节点，因为节点的val和节点一一对应，因此可以用val来作为map的key，克隆后得到的新节点为value
// 对于一个节点oldNode，若它是null，则直接返回null
// 若它已经被克隆过（即存在于map中），则直接从map中将其取出并返回
// 否则，oldNode是一个新的节点，遍历它的neighbors，并递归调用cloneByDFS方法

class Solution {
    private Map<Integer, Node> map = new HashMap<>();
    public Node cloneGraph(Node node) {
        return cloneByDFS(node);
    }
    private Node cloneByDFS(Node oldNode) {
        if (oldNode == null) {
            return null;
        }
        Node clonedNode;
        if (!map.containsKey(oldNode.val)) {
            clonedNode = new Node(oldNode.val);
            map.put(oldNode.val, clonedNode);
            for (Node neighbor : oldNode.neighbors) {
                clonedNode.neighbors.add(cloneByDFS(neighbor));
            }
        } else {
            clonedNode = map.get(oldNode.val);
        }
        return clonedNode;
    }
}

// 解法2：BFS
// 思路：与DFS类似，用一个map来存储已经克隆过的node.val和克隆得到的node之间的映射关系
// 对于当前节点oldNode的每一个neighbor，如果这个neighbor不存在于map中，说明这是一个新的节点，需要被克隆
// 因此在map中添加这样一个新的映射，并把neighbor插入到队列中
// 最后，还要通过当前节点的val值从map中取出当前节点对应的克隆节点，然后在该克隆节点的neighbors中插入neighbor在map中对应的克隆节点
// 注意这一步需要在if条件之外，因为当前的oldNode对应的克隆节点在被添加到map中时其neighbors列表是空的，因此需要把所有neighbor对应的可用节点
// 都添加到当前节点的克隆节点的neighbors中，这与map中是否已经包含neighbor映射的克隆节点无关

class BFSSolution {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        Map<Integer, Node> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        map.put(node.val, new Node(node.val));
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node oldNode = queue.poll();
            for (Node neighbor : oldNode.neighbors) {
                if (!map.containsKey(neighbor.val)) {
                    Node newNeighbor = new Node(neighbor.val);
                    map.put(neighbor.val, newNeighbor);
                    queue.offer(neighbor);
                }
                map.get(oldNode.val).neighbors.add(map.get(neighbor.val));
            }
        }
        return map.get(node.val);
    }
}