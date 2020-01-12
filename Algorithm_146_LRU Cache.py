# 思路：用map + 双向链表存储节点。每个节点表示一个key-value pair
# 访问某个已存在的节点时，将其从原来的位置取出，插入到链表头部
# 当插入一个新节点时，将其插入到链表的头部
# 然后判断链表的长度是否超标，若超出限定的capacity，则删除链表尾部元素
# 注意特殊情况：某个key对应的value被更新，此时cache的size并不改变

class LRUCache:
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.map = {}
        self.head = Node(0, 0)
        self.tail = Node(0, 0)
        self.head.next, self.tail.prev = self.tail, self.head

    def get(self, key: int) -> int:
        if key not in self.map:
            return -1
        node = self.map[key]
        # remove node from its previous position
        self._remove(node)
        # add node to the head of the list
        self._put(node)
        return node.value

    def put(self, key: int, value: int) -> None:
        if key in self.map:
            self._remove(self.map[key])
        toPut = Node(key, value)
        self._put(toPut)
        self.map[key] = toPut
        # if size of the cache is greater than capacity, remove the tail
        # this must be done after put is done, because it is possible that
        # the put operation is an udpate to an existing entry, for example,
        # put(1, 2), put(1, 5)
        if len(self.map) > self.capacity:
            toRemove = self.tail.prev
            self._remove(toRemove)
            del self.map[toRemove.key]

    def _remove(self, node: 'Node') -> None:
        p, n = node.prev, node.next
        p.next = n
        n.prev = p

    def _put(self, node: 'Node') -> None:
        node.prev = self.head
        node.next = self.head.next
        self.head.next.prev = node
        self.head.next = node

class Node:
    def __init__(self, key: int, value: int):
        self.key = key
        self.value = value
        self.prev = None
        self.next = None

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)