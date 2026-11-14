from math import inf


class Solution:
    def maxProfit(self, prices: list[int]) -> int:
        # 假设今天卖出，最好的买入价就是今天之前的最低价
        mintag, profit = inf, 0
        for p in prices:
            mintag = min(p, mintag)
            profit = max(p - mintag, profit)
        return profit
