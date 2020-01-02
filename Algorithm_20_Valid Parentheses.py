class Solution:
    def isValid(self, s: str) -> bool:
        map = {')': '(', ']': '[', '}': "{"}
        stack = []
        for c in s:
            if c == ')' or c == ']' or c == '}':
                popped = None if len(stack) == 0 else stack.pop()
                if popped != map[c]: # not match
                    return False
            else:
                stack.append(c)
        return len(stack) == 0