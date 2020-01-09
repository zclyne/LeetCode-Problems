class Solution:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        if not intervals:
            return []
        intervals.sort(key=lambda x: x[0])
        result = []
        currentStart, currentEnd = intervals[0][0], intervals[0][1]
        for i in range(1, len(intervals)):
            curInterval = intervals[i]
            if curInterval[0] <= currentEnd: # can merge this interval
                currentEnd = max(currentEnd, curInterval[1])
            else: # cannot merge current interval
                result.append([currentStart, currentEnd])
                currentStart = curInterval[0]
                currentEnd = curInterval[1]
        result.append([currentStart, currentEnd])
        return result