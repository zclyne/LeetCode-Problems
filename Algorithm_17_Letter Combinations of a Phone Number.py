# 递归解法
class Solution:
    def __init__(self):
        self.charsMapping = {'2': 'abc','3': 'def','4': 'ghi','5': 'jkl','6': 'mno','7': 'pqrs','8': 'tuv','9': 'wxyz'}

    def letterCombinations(self, digits: str) -> List[str]:
        if len(digits) == 0:
            return []
        return self.helper(digits, 0)
    
    def helper(self, digits: str, start: int) -> List[str]:
        # if there is only one digit left
        if start == len(digits) - 1:
            return list(self.charsMapping[digits[start]])
        result = []
        currentMappingChars = self.charsMapping[digits[start]]
        alreadyMappedStrs = self.helper(digits, start + 1)
        return [c + alreadyMappedStr for c in currentMappingChars for alreadyMappedStr in alreadyMappedStrs]

# 队列解法
class QueueSolution:
    def letterCombinations(self, digits: str) -> List[str]:
        if not digits:
            return []
        charsMapping = {'2': 'abc','3': 'def','4': 'ghi','5': 'jkl','6': 'mno','7': 'pqrs','8': 'tuv','9': 'wxyz'}
        result = ['']
        for digit in digits:
            currentMappingChars = charsMapping[digit]
            size = len(result)
            for _ in range(size):
                tmp = result.pop(0)
                for c in currentMappingChars:
                    result.append(tmp + c)
        return result