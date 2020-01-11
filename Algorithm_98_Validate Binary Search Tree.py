# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

import sys

class Solution:
    def isValidBST(self, root: TreeNode) -> bool:
        if not root:
            return True
        return self.isValid(root.left, -sys.maxsize - 1, root.val) and \
               self.isValid(root.right, root.val, sys.maxsize)
        
    def isValid(self, root: TreeNode, lower: int, upper: int) -> bool:
        if not root:
            return True
        elif root.val <= lower or root.val >= upper:
            return False
        return self.isValid(root.left, lower, root.val) and \
               self.isValid(root.right, root.val, upper)