class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:
    def minDiffInBST(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        if root==None: return float('inf')
        if root.left!=None:
            tmpLeft=root.left
            while tmpLeft.right!=None: tmpLeft=tmpLeft.right
            leftDif=root.val-tmpLeft.val
        else: leftDif=float('inf')
        if root.right!=None:
            tmpRight=root.right
            while tmpRight.left!=None: tmpRight=tmpRight.left
            rightDif=tmpRight.val-root.val
        else: rightDif=float('inf')
        return min(leftDif,rightDif,Solution.minDiffInBST(self,root.left),Solution.minDiffInBST(self,root.right))