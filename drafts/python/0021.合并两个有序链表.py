# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def mergeTwoLists(self, list1: Optional[ListNode], list2: Optional[ListNode]) -> Optional[ListNode]:
        dummy = TreeNode()
        while list1 and list2:
            if list1.val < list2.val:
                dummy.next, list1 = list1, list1.next
            else:
