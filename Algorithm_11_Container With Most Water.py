class Solution:
    def maxArea(self, heights):
        minHeight=min(heights[0],heights[len(heights)-1])
        maxVol=(len(heights)-1)*minHeight
        i,j=0,len(heights)-1
        while i<j:
            while heights[i]<=minHeight and i<j: i+=1
            while heights[j]<=minHeight and i<j: j-=1
            minHeight=min(heights[i],heights[j])
            if (j-i)*minHeight>maxVol: maxVol=(j-i)*minHeight
        return maxVol