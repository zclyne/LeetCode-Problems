class Solution:
    def countBits(self, num: int) -> List[int]:
        res = [0 for _ in range(num + 1)]
        for i in range(num + 1):
            res[i] = res[i >> 1] + (i & 1)
        return res