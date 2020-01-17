class Solution:
    def decodeString(self, s: str) -> str:
        strStack, countStack = [], []
        cur = ""
        count = 0
        for ch in s:
            if ch.isdigit():
                count = count * 10 + int(ch)
            elif ch == '[':
                countStack.append(count)
                strStack.append(cur)
                cur = ""
                count = 0
            elif ch == ']':
                tmpCount = countStack.pop()
                tmpStr = strStack.pop()
                tmpStr += cur * tmpCount
                cur = tmpStr
            else:
                cur += ch
        return cur