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