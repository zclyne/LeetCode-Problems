class Solution:
    def minCostClimbingStairs(self, cost):
        """
        :type cost: List[int]
        :rtype: int
        """
        cost.append(0)
        arr=[cost[0],cost[1]]
        for i in range(2,len(cost)): arr.append(min(arr[i-1],arr[i-2])+cost[i])
        return arr[len(arr)-1]