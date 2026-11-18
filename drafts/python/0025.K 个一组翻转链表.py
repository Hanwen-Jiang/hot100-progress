# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def reverseKGroup(self, head: Optional[ListNode], k: int) -> Optional[ListNode]:
        dummy = ListNode(0, head)
        kth, last = dummy, dummy
        while True:
            for _ in range(k):
                kth = kth.next
                if kth == None:
                    return dummy.next
            prev, cur = last.next, kth
