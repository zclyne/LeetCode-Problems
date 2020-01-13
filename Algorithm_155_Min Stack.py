class MinStack:

    def __init__(self):
        self.stack = []
        self.min = None

    def push(self, x: int) -> None:
        # x is the new min element
        # must use <= here, because one element may be pushed for many times
        # use self.min == None instead of not self.min, because not 0 == True
        if self.min == None or x <= self.min:
            self.stack.append(self.min)
            self.min = x
        self.stack.append(x)

    def pop(self) -> None:
        if not self.stack:
            return
        x = self.stack.pop()
        # x is the min element, update self.min
        if x == self.min:
            self.min = self.stack.pop()

    def top(self) -> int:
        return self.stack[-1]

    def getMin(self) -> int:
        return self.min

# Your MinStack object will be instantiated and called as such:
# obj = MinStack()
# obj.push(x)
# obj.pop()
# param_3 = obj.top()
# param_4 = obj.getMin()