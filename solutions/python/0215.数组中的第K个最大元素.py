import random
from typing import List


class Solution:
    def findKthLargest(self, nums: List[int], k: int) -> int:
        pivot = random.choice(nums)
        left = [x for x in nums if x > pivot]
        right = [x for x in nums if x < pivot]
        if len(left) >= k:
            return self.findKthLargest(left, k)
        elif len(right) > len(nums) - k:
            return self.findKthLargest(right, k + len(right) - len(nums))
        return pivot
