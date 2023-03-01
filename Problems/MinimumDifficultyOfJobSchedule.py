from functools import lru_cache
from typing import List


class Solution:
    def minDifficulty(self, jobDifficulty: List[int], d: int) -> int:
        # states: day d, jobs done
        # using top-down dp
        # base case: 0 jobs done + 1, 1 day
        # first need to check if jobs >= days
        # recursive call
        # if current day is the last day, 
        #   then we need to return the hardest job between the j state and the end
        # need the best choice within the range from j state (jobs done + 1) to n - (d - day)
        # need to keep track of the hardest job with in the range above
        # recursive call will return the best choice 
        
        n = len(jobDifficulty)
        if n < d:
            return -1
        
        maxJobremaining = [0] * n
        maxNum = 0
        for x in range(n - 1, -1, -1):
            maxNum = max(maxNum, jobDifficulty[x])
            maxJobremaining[x] = maxNum
        
        
        @lru_cache(None)
        def dp(j, day):
            if day == d:
                return maxJobremaining[j]

            best = float("inf")
            hardest = 0
            for i in range(j, n - (d - day)):
                hardest = max(hardest, jobDifficulty[i])
                best = min(best, hardest + dp(i + 1, day + 1))

            return best

        return dp(0, 1)
    
    # bottom-up approach
    def minDifficulty2(self, jobDifficulty: List[int], d: int) -> int:
        n = len(jobDifficulty)
        if n < d:
            return -1
        
        # states: jobs that are done, remaining days
        
        dp = [[float("inf")] * (d + 1) for _ in range(n)]

        # base case
        dp[-1][d] = jobDifficulty[-1]

        for i in range(n - 2, -1, -1):
            dp[i][d] = max(dp[i + 1][d], jobDifficulty[i])

        for day in range(d - 1, 0, -1): # fix day
            for i in range(day - 1, n - (d - day)): # fix the beginning task in the day
                hardest = 0
                for j in range(i, n - (d - day)): # loop through from the beginning to max task that can be done
                    hardest = max(hardest, jobDifficulty[j]) # determine the hardest task among chosen tasks
                    dp[i][day] = min(dp[i][day], hardest + dp[j + 1][day + 1]) # update

        return dp[0][1]