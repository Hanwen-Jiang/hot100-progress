import heapq
from typing import List


class Solution:
    def findKthLargest(self, nums: List[int], k: int) -> int:
        heap = nums[:k]
        heapq.heapify(heap)             # 先放前 k 个，建小顶堆
        for x in nums[k:]:
            if x > heap[0]:             # 比堆顶大，才有资格进前 k
                heapq.heapreplace(heap, x)
        return heap[0]
