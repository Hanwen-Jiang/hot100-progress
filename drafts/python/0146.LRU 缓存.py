class ListNode:
    def __init__(self, key=None, value=None, prev=None, next=None):
        self.key = key
        self.value = value
        self.prev = prev
        self.next = next

class LRUCache:

    def __init__(self, capacity: int):
        self._map = {}
        self.capacity = capacity
        self.tail, self.dummy = ListNode(), ListNode()
        self.tail.prev, self.dummy.next = self.dummy, self.tail

    def _delete(self, key):
        x = self.dummy
        while x is not None:
            x = x.next
            if x.key == key:
                x.prev.next, x.next.prev = x.next, x.prev
        return

    def _popleft(self, key, value):
        p = dummy.next
        x = ListNode(key, value, dummy, p)
        p.prev = x

    def get(self, key: int) -> int:

    def put(self, key: int, value: int) -> None:
        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
