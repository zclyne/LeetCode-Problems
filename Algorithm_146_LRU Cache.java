import java.util.HashMap;
import java.util.Map;

// 思路：定义一个内部类Node，表示一个链表中的节点
// 用map来存储key到链表中节点Node的映射
// 对于一个get操作，首先判断key是否存在于map中，若不存在，则直接返回-1
// 若存在，则从map中取出相应的node，然后把这个node插入到链表的头部
// 对于一个put操作，首先判断key是否存在于map中
// 若存在，则从map中取出相应的node，更新这个node的value，然后把这个node插入到链表头部
// 若不存在，则新建一个node插入到链表头部
// 如果此时cache已满，还要从链表中取出最后一个节点，这个节点就是要evict的节点
// 从这个节点中取出key，通过key从map中删除value，并把它从链表中也删除

class LRUCache {

    private class Node {
        private Node pre;
        private Node next;
        private int key;
        private int val;
        private Node(int key, int val) {
            this.pre = null;
            this.next = null;
            this.key = key;
            this.val = val;
        }
    }

    private Node dummyHead;
    private Node dummyTail;
    private Map<Integer, Node> map;

    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        dummyHead = new Node(0, 0);
        dummyTail = new Node(0, 0);
        dummyHead.next = dummyTail;
        dummyTail.pre = dummyHead;
    }
    
    public int get(int key) {
        Node node = map.getOrDefault(key, null);
        if (node == null) {
            return -1;
        }
        // move node to the head of the linkedlist
        // 1. move node out of its original position
        node.pre.next = node.next;
        node.next.pre = node.pre;
        // 2. connect node to the head of the linkedlist
        insertToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) { // map already contains key, update the value and move it to the head
            Node node = map.get(key);
            node.val = value;
            // move node out of its original positiion
            node.pre.next = node.next;
            node.next.pre = node.pre;
            insertToHead(node);
        } else { // this is a new key
            if (map.size() == capacity) { // cache is already full, evict the LRU node
                Node nodeToEvict = dummyTail.pre;
                Node preNode = nodeToEvict.pre;
                int keyToEvict = nodeToEvict.key;
                map.remove(keyToEvict);
                preNode.next = dummyTail;
                dummyTail.pre = preNode;
            }
            // connect the new node to the head of the linkedlist
            Node nodeToInsert = new Node(key, value);
            insertToHead(nodeToInsert);
            // add new node to map
            map.put(key, nodeToInsert);
        }
    }

    private void insertToHead(Node node) {
        node.pre = dummyHead;
        node.next = dummyHead.next;
        dummyHead.next.pre = node;
        dummyHead.next = node;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */