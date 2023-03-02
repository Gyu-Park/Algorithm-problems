from functools import lru_cache
from typing import List


class Solution:
    # bottom-up dp
    def coinChange(self, coins: List[int], amount: int) -> int:
        dp = [float('inf')] * (amount + 1)
        dp[0] = 0
        
        for coin in coins:
            for x in range(coin, amount + 1):
                dp[x] = min(dp[x], dp[x - coin] + 1)
        return dp[amount] if dp[amount] != float('inf') else -1
    
    def coinChange(self, coins: List[int], amount: int) -> int:
        # states: minimum number of coins in each amount
        # top-down approach
        @lru_cache(None)
        def dp(cur_amount):
            if cur_amount == 0:
                return 0
            if cur_amount < 0:
                return -1
            
            min_coins = float("inf")
            for coin in coins:
                res = dp(cur_amount - coin)
                if res != -1:
                    min_coins = min(min_coins, res + 1)

            return min_coins if min_coins != float("inf") else -1
            
        return dp(amount)