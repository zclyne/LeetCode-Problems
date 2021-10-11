import java.util.*;

class LRUCache {

    class Node {
        public int key;
        public int value;
        public Node prev;
        public Node next;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    private Node dummyHead;
    private Node dummyTail;
    private Map<Integer, Node> map;
    private int capacity;

    public LRUCache(int capacity) {
        dummyHead = new Node(-1, 0);
        dummyTail = new Node(-1, 0);
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
        map = new HashMap<>();
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        // cut node from its original position
        node.prev.next = node.next;
        node.next.prev = node.prev;
        // insert node to the head of the linked list
        insertToHead(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        Node node = null;
        if (map.containsKey(key)) {
            node = map.get(key);
            node.value = value;
            // cut node from its original position
            node.prev.next = node.next;
            node.next.prev = node.prev;
        } else {
            node = new Node(key, value);
            if (map.size() == this.capacity) { // evict the least recently used node
                Node toRemove = dummyTail.prev;
                toRemove.prev.next = dummyTail;
                dummyTail.prev = toRemove.prev;
                map.remove(toRemove.key);
            }
        }
        map.put(key, node);
        insertToHead(node);
    }

    private void insertToHead(Node node) {
        node.prev = dummyHead;
        node.next = dummyHead.next;
        dummyHead.next.prev = node;
        dummyHead.next = node;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */