# 思路：
# 遍历两字符串，数组secretCount和guessCount中记录当前数字出现的次数
# 若当前位置上的字符相等，则bulls += 1，不记录在两数组中
# 否则，数组secretCount和guessCount对应位置+1
# 最后，再遍历一遍两数组，在各个位置上都取较小值加到cows上
# 较小值代表当前数字出现在两字符串中出现次数较少的哪一个

class Solution:
    def getHint(self, secret: str, guess: str) -> str:
        secretCount = [0 for _ in range(10)]
        guessCount = [0 for _ in range(10)]
        bulls = cows = 0
        for i in range(len(secret)):
            n1 = int(secret[i])
            n2 = int(guess[i])
            if n1 == n2:
                bulls += 1
            else:
                secretCount[n1] += 1
                guessCount[n2] += 1
        for i in range(10):
            cows += min(secretCount[i], guessCount[i])
        return str(bulls) + 'A' + str(cows) + 'B'
    

    
class SolutionOptimized:
    def getHint(self, secret: str, guess: str) -> str:
        numsCount = [0 for _ in range(10)]
        bulls = cows = 0
        for i in range(len(secret)):
            n1 = int(secret[i])
            n2 = int(guess[i])
            if n1 == n2:
                bulls += 1
            else:
                if numsCount[n1] < 0:
                    cows += 1
                if numsCount[n2] > 0:
                    cows += 1
                numsCount[n1] += 1
                numsCount[n2] -= 1
        return str(bulls) + 'A' + str(cows) + 'B'