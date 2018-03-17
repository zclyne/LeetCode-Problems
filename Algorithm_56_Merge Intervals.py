class Interval:
    def __init__(self, s=0, e=0):
        self.start = s
        self.end = e

class Solution:
    def merge(self, intervals):
        if len(intervals)<2:
            return intervals
        intervals.sort(key=lambda Interval:Interval.start)
        res=[intervals[0]]
        for i in range(len(intervals)-1):
            if intervals[i+1].start<=res[len(res)-1].end and intervals[i+1].end>res[len(res)-1].end:
                res[len(res)-1].end=intervals[i+1].end
            elif intervals[i+1].start>res[len(res)-1].end:
                res.append(intervals[i+1])
        return res
