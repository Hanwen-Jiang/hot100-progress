#
# @lc app=leetcode.cn id=108 lang=python3
#
# [108] 将有序数组转换为二叉搜索树
#

# @lc code=start
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def sortedArrayToBST(self, nums: list[int]) -> TreeNode | None:
        def build(left, right):
            if left > right:
                return None
            mid = (left + right) // 2
            return TreeNode(nums[mid], build(left, mid-1), build(mid+1, right))
        return build(0, len(nums)-1)
        
# @lc code=end

