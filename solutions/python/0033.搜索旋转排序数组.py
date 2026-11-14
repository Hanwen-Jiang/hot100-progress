class Solution:
    def search(self, nums: list[int], target: int) -> int:
        low, high = 0, len(nums) - 1
        while low <= high:
            mid = (low + high) // 2
            if nums[mid] == target:
                return mid
            elif nums[high] == target:
                return high
            elif nums[low] == target:
                return low
            if nums[low] <= nums[mid]:          # 左半段 [low, mid] 有序
                if nums[low] < target < nums[mid]:
                    high = mid - 1
                else:
                    low = mid + 1
            else:                               # 右半段 [mid, high] 有序
                if nums[mid] < target < nums[high]:
                    low = mid + 1
                else:
                    high = mid - 1
        return -1
