# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:
    def levelOrder(self, root: TreeNode) -> List[List[int]]:
        queue = [root]
        res = []
        while queue:
            queueLen = len(queue)
            cur = []
            for i in range(queueLen):
                curRoot = queue.pop(0)
                if not curRoot:
                    continue
                cur.append(curRoot.val)
                queue.append(curRoot.left)
                queue.append(curRoot.right)
            if cur:
                res.append(cur[:])
        return res