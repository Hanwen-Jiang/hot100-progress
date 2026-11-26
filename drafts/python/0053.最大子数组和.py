class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        last = best = nums[0]
        for x in nums[1:]:
            last = max(last+x, x)
            best = max(best, last)
        return best
