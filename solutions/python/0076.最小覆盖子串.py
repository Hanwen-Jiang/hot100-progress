from collections import Counter
class Solution:
    def minWindow(self, s: str, t: str) -> str:
        c, l, size, resl = Counter(t), 0, float('inf'), 0
        for i, x in enumerate(s):
            c[x] -= 1
            while not any([k>0 for k in c.values()]):
                if size > i-l+1:
                    size = i-l+1
                    resl = l
                c[s[l]] += 1
                l+=1
        return s[resl:resl+size] if size != float('inf') else ''
