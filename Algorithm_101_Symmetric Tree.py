# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:
    def isSymmetric(self, root: TreeNode) -> bool:
        if not root:
            return True
        return self.helper(root.left, root.right)
    
    def helper(self, left: TreeNode, right: TreeNode) -> bool:
        if not left and not right:
            return True
        elif not left and right or not right and left:
            return False
        return left.val == right.val and \
            self.helper(left.left, right.right) and \
            self.helper(left.right, right.left)