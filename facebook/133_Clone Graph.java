import java.util.*;

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

// BFS

class Solution {
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