from collections import Counter
class Solution:
    def minWindow(self, s: str, t: str) -> str:
        c, l, size = Counter(t), 0, float('inf')
        for i, x in enumerate(s):
            c[x] -= 1
