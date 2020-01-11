# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:
    def flatten(self, root: TreeNode) -> None:
        self.prev = None
        self.helper(root)
        
    def helper(self, root: TreeNode) -> None:
        if not root:
            return
        self.helper(root.right)
        self.helper(root.left)
        root.right = self.prev
        root.left = None
        self.prev = root