class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        m = {}
        for i, a in enumerate(s):
            if a not in m:
                m[a] = i
