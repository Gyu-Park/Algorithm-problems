from bisect import bisect_right
from itertools import accumulate
from typing import List


def answerQueries(nums: List[int], queries: List[int]) -> List[int]:
    nums = list(accumulate(sorted(nums)))
    return [bisect_right(nums, q) for q in queries]


print(answerQueries([4, 5, 2, 1], [3, 10, 21]))
