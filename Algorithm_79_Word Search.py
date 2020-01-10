class Solution:
    def exist(self, board: List[List[str]], word: str) -> bool:
        if len(board) == 0 or len(board[0]) == 0:
            return False
        for i in range(len(board)):
            for j in range(len(board[0])):
                if self.matchWord(board, word, i, j, 0):
                    return True
        return False

    def matchWord(self, board: List[List[str]], word: str, i: int, j: int, pos: int) -> bool:
        if pos == len(word): # successfully find a match
            return True
        if i < 0 or i == len(board) or j < 0 or j == len(board[0]) or word[pos] != board[i][j]:
            return False
        tmp = board[i][j]
        board[i][j] = "#"
        res = self.matchWord(board, word, i - 1, j, pos + 1) or \
              self.matchWord(board, word, i + 1, j, pos + 1) or \
              self.matchWord(board, word, i, j - 1, pos + 1) or \
              self.matchWord(board, word, i, j + 1, pos + 1)
        board[i][j] = tmp
        return res