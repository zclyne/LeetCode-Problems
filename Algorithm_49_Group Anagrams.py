class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        map = {}
        for word in strs:
            key = tuple(sorted(word))
            map[key] = map.get(key, []) + [word]
        return list(map.values())