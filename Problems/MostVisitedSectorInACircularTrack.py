from typing import List


def mostVisited(n: int, A: List[int]) -> List[int]:
    return range(A[0], A[-1] + 1) if A[0] <= A[-1] else list(range(1, A[-1] + 1)) + list(range(A[0], n + 1))

rounds = [1,3,5,7]
print(mostVisited(7, rounds))