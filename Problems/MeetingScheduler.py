from typing import List


class Solution:
    def minAvailableDuration(self, slots1: List[List[int]], slots2: List[List[int]], duration: int) -> List[int]:
        slots1.sort()
        slots2.sort()
        count1, count2 = 0, 0
        while len(slots1) > count1 and len(slots2) > count2:
            slot1 = slots1[count1]
            slot2 = slots2[count2]
            dur = min(slot1[1], slot2[1]) - max(slot1[0], slot2[0])
            start = max(slot1[0], slot2[0])
            if dur >= duration:
                return [start, start + duration]
            if slot1[1] > slot2[1]:
                count2 += 1
            elif slot1[1] < slot2[1]:
                count1 += 1
            else:
                count1 += 1
                count2 += 1
        return []
