class Node:
    def __init__(self):
        self.isWord = False
        self.children = [None for _ in range(26)]

class Trie:

    def __init__(self):
        self.roots = [None for _ in range(26)]

    def insert(self, word: str) -> None:
        cur = self.roots
        for i in range(len(word)):
            char = word[i]
            idx = ord(char) - ord('a')
            if not cur[idx]:
                cur[idx] = Node()
            if i == len(word) - 1:
                cur[idx].isWord = True
            cur = cur[idx].children
            

    def search(self, word: str) -> bool:
        cur = self.roots
        for i in range(len(word)):
            char = word[i]
            idx = ord(char) - ord('a')
            if not cur[idx] or i == len(word) - 1 and cur[idx].isWord == False:
                return False
            cur = cur[idx].children
        return True

    def startsWith(self, prefix: str) -> bool:
        cur = self.roots
        for char in prefix:
            idx = ord(char) - ord('a')
            if not cur[idx]:
                return False
            cur = cur[idx].children
        return True


# Your Trie object will be instantiated and called as such:
# obj = Trie()
# obj.insert(word)
# param_2 = obj.search(word)
# param_3 = obj.startsWith(prefix)