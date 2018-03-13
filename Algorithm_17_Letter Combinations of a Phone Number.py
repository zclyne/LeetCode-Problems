import queue
class Solution:
    def letterCombinations(self, digits):
        res=[]
        if len(digits)==0: return res
        dic={'1':'','2':'abc','3':'def','4':'ghi','5':'jkl','6':'mno','7':'pqrs','8':'tuv','9':'wxyz','0':''}
        que=queue.Queue()
        thisStr,nextStr='',''
        for i in range(len(digits)):
            if que.empty()==False:
                thisStr=que.get()
            mappedStr=dic[digits[i]]
            for j in range(len(mappedStr)):
                que.put(thisStr+mappedStr[j])
            if que.empty()==False:
                nextStr=que.get()
            while que.empty()==False and len(nextStr)==len(thisStr):
                for j in range(len(mappedStr)):
                    que.put(nextStr+mappedStr[j])
                nextStr=que.get()
            que.put(nextStr)
        while que.empty()==False: res.append(que.get())
        return res