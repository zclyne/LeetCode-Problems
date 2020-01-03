class Solution:
    def generateParenthesis(self, n: int) -> List[str]:
        if n == 0:
            return []
        self.result = []
        self.helper(n, 0, 0, "")
        return self.result
    def helper(self, n: int, num_used_left: int, num_used_right: int, s: str):
        if 2 * n == num_used_left + num_used_right: # already used all parentheses
            self.result.append(s)
        if num_used_left < n: # have remaining left parentheses to use
            self.helper(n, num_used_left + 1, num_used_right, s + '(')
        if num_used_right < num_used_left: # can append a right parenthesis
            self.helper(n, num_used_left, num_used_right + 1, s + ')')