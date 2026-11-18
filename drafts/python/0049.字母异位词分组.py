from itertools import groupby

class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        return [list(g) for _, g in groupby(sorted(strs, key=sorted), key=sorted)]
