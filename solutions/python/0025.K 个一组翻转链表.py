# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def reverseKGroup(self, head: Optional[ListNode], k: int) -> Optional[ListNode]:
        dummy = ListNode(0, head)
        kth = dummy
        while True:
            gprev = kth
            for _ in range(k):
                kth = kth.next
                if kth == None:
                    return dummy.next
            gnext = kth.next
            prev, cur = gnext, gprev.next
            while cur != gnext:
                cur.next, cur, prev = prev, cur.next, cur
            kth, gprev.next = gprev.next, prev
        return None
