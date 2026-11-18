class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        m, l, res = {}, 0, 0
        for i, a in enumerate(s):
            if a in m:
                l = m[a]+1
            m[a] = i
            res = max(res, i-l+1)
        return res
