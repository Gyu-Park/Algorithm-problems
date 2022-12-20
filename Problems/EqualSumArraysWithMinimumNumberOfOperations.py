from typing import Counter, List

# calculate difference between nums1 and nums2
# make Counter to count 6-x for nums1(small sum) and x-1 for nums2(big sum)
# set i as 5 and -1 for each counter 5, 4, 3, 2, 1 untill difference equal or less than 0


def minOperations(nums1: List[int], nums2: List[int]) -> int:
    diff = sum(nums2) - sum(nums1)
    if diff < 0:
        return minOperations(nums2, nums1)
    counter = Counter([6-x for x in nums1] + [x-1 for x in nums2])
    i, steps = 5, 0
    while diff > 0:
        if i == 0:
            return -1
        if counter[i] == 0:
            i -= 1
        else:
            diff, counter[i], steps = diff-i, counter[i]-1, steps+1
    return steps


nums1 = [1, 2, 3, 4, 5, 6]
nums2 = [1, 1, 2, 2, 2, 2]
print(minOperations(nums1, nums2))
