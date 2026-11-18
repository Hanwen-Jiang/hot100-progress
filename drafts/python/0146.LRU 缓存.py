class ListNode:
    def __init__(self, key=None, prev=None, next=None):
        self.key = key
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
                delete self._map[key]
                return

    def _popleft(self, key, value):
        p = dummy.next
        x = ListNode(key, dummy, p)
        p.prev = x
        self._map[key] = value

    def get(self, key: int) -> int:
        if key in self._map:
    def put(self, key: int, value: int) -> None:
        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
