# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.
"""

class Solution:
    def countBinarySubstrings(self, s):
        """
        :type s: str
        :rtype: int
        """
        count=0
        last_0,last_1=0,0
        used_0,used_1=0,0
        for i in range(len(s)):
            if s[i]=='0':
                if s[i-1]=='1':
                    last_0=0
                    used_1=0
                last_0+=1
                if (used_1<last_1):
                    used_1+=1
                    count+=1
            elif s[i]=='1':
                if s[i-1]=='0':
                    last_1=0
                    used_0=0
                last_1+=1
                if (used_0<last_0):
                    used_0+=1
                    count+=1
        return count

sol=Solution()
print (sol.countBinarySubstrings("00110"))