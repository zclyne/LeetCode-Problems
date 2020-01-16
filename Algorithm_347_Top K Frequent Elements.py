class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        buckets = [[] for _ in range(len(nums) + 1)]
        res = []
        map = {}
        for num in nums:
            count = map.get(num, 0)
            map[num] = count + 1
        for num in map:
            count = map[num]
            buckets[count].append(num)
        for i in range(len(nums), 0, -1):
            bucket = buckets[i]
            if bucket:
                for n in bucket:
                    res.append(n)
                    k -= 1
                    if k == 0:
                        return res