from collections import deque
import collections


class HitCounter:

    def __init__(self):
        self.counter = deque()

    def hit(self, timestamp: int) -> None:
        self.counter.append(timestamp)

    def getHits(self, timestamp: int) -> int:
        while self.counter and self.counter[0] + 300 <= timestamp:
            self.counter.popleft()
        return len(self.counter)


class HitCounter2:

    def __init__(self):
        self.hits = collections.defaultdict(int)

    def hit(self, timestamp: int) -> None:
        self.hits[timestamp] += 1

    def getHits(self, timestamp: int) -> int:
        return sum([self.hits[i] for i in range(max(1, timestamp - 300 + 1), timestamp + 1)])
