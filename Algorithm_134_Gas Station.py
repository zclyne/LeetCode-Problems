# 思路：有以下两条结论
# 1. 若以station A为起点时，无法到达station B，则以任何A和B之间的station作为起点都无法到达station B
# 2. 若gas总量 > cost总量，则答案必定存在

class Solution:
    def canCompleteCircuit(self, gas: List[int], cost: List[int]) -> int:
        curGas = start = totalGas = totalCost = 0
        for i in range(len(gas)):
            curGas += gas[i] - cost[i]
            totalGas += gas[i]
            totalCost += cost[i]
            if curGas < 0: # cannot reach next station, select a new start
                start = i + 1
                curGas = 0
        return -1 if totalGas < totalCost else start