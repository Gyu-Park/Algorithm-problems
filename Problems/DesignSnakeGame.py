import collections
from typing import List


class SnakeGame:

    def __init__(self, width: int, height: int, food: List[List[int]]):
        self.width = width - 1
        self.height = height - 1
        food.reverse()
        self.food = food
        self.row = 0
        self.col = 0
        self.dq = collections.deque()
        self.bodyLen = 0

    def setLocation(self, direction):
        if direction == "R":
            self.col += 1
        elif direction == "L":
            self.col -= 1
        elif direction == "U":
            self.row -= 1
        elif direction == "D":
            self.row += 1

    def move(self, direction: str) -> int:
        self.setLocation(direction)
        if self.row < 0 or self.col < 0 or self.row > self.height or self.col > self.width:
            return -1
        if (self.row, self.col) in self.dq:
            return -1
        self.dq.append((self.row, self.col))
        if len(self.food) > 0 and self.food[-1][0] == self.row and self.food[-1][1] == self.col:
            self.food.pop()
            self.bodyLen += 1
            return self.bodyLen
        self.dq.popleft()
        return self.bodyLen


# Your SnakeGame object will be instantiated and called as such:
# obj = SnakeGame(width, height, food)
# param_1 = obj.move(direction)
