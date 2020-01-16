# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:
    def rob(self, root: TreeNode) -> int:
        return max(self.helper(root))

    def helper(self, root: TreeNode) -> tuple:
        if not root:
            return (0, 0)
        (leftRob, leftNotRob) = self.helper(root.left)
        (rightRob, rightNotRob) = self.helper(root.right)
        rootRob = root.val + leftNotRob + rightNotRob
        rootNotRob = max(leftRob, leftNotRob) + max(rightRob, rightNotRob)
        return (rootRob, rootNotRob)