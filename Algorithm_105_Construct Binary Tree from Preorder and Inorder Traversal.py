# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:
    def buildTree(self, preorder: List[int], inorder: List[int]) -> TreeNode:
        if not preorder or not inorder:
            return None
        idx = inorder.index(preorder[0])
        # get num of nodes in left subtree and right subtree
        leftNum, rightNum = idx, len(preorder) - idx - 1
        root = TreeNode(preorder[0])
        root.left = self.buildTree(preorder[1:1 + leftNum], inorder[0:idx])
        root.right = self.buildTree(preorder[1 + leftNum:], inorder[idx + 1:])
        return root