class ListNode:
    def __init__(self, key=None, value=None, prev=None, next=None):
        self.key = key
        self.prev = prev
        self.value = value
        self.next = next

class LRUCache:

    def __init__(self, capacity: int):
        self._set = set([])
        self.capacity = capacity
        self.tail, self.dummy = ListNode(), ListNode()
        self.tail.prev, self.dummy.next = self.dummy, self.tail

    def _delete(self, key):
        x = self.dummy.next
        while x is not None:
            if x.key == key:
                x.prev.next, x.next.prev = x.next, x.prev
                self.capacity+=1
                self._set.remove(key)
                return x.value
            x = x.next
        return -1

    def _popleft(self, key, value):
        p = self.dummy.next
        x = ListNode(key, value, self.dummy, p)
        p.prev, self.dummy.next = x, x
        self.capacity-=1
        self._set.add(key)

    def get(self, key: int) -> int:
        x=self._delete(key)
        if x != -1:
            self._popleft(key, x)
        return x

    def put(self, key: int, value: int) -> None:
        if key in self._set:
            self._delete(key)
        self._popleft(key, value)
        if self.capacity < 0:
            x = self.tail.prev.prev
            x.next.next, x.next.prev = None, None
            x.next, self.tail.prev = self.tail, x


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
