class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        last = best = nums[0]
        for x in nums:
            last = max(last+x, x)
            best = max(best, last)
        return best
