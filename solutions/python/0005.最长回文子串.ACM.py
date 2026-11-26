import sys
from typing import List, Optional, Tuple, Dict, Set

# —— 核心解法 ——
class Solution:
    def longestPalindrome(self, s: str) -> str:
        def grow(l, r):
            while l >= 0 and r < len(s) and s[l] == s[r]:
                l, r = l - 1, r + 1
            return s[l + 1:r]
        return max((grow(i, j) for i in range(len(s)) for j in (i, i + 1)), key=len)

# —— ACM 标准输入输出 ——
_SIG = {"fn":"longestPalindrome","params":["string"],"ret":"string","cmp":"length"}
_lines = sys.stdin.read().replace("\r", "").split("\n")
_at = 0
def _next():
    global _at
    s = _lines[_at] if _at < len(_lines) else ""
    _at += 1
    return s
def _item(t):
    return t[:-2] if t.endswith("[]") else t[5:-1] if t.startswith("List<") else None
def _atom(s, t):
    if t in ("string", "char"):
        return s
    if t == "bool":
        return s in ("true", "True", "1")
    if s == "null" or (t == "NodeIndex" and s == "-1"):
        return None
    return float(s) if t == "double" else int(s)
def _read_tokens(cnt, item):
    toks = []
    while len(toks) < cnt:
        toks.extend(_next().split())
    return [_atom(x, item) for x in toks]
def _read(t):
    item = _item(t)
    if item is None:
        return _atom(_next(), t)
    cnt = int(_next())
    if item in ("int", "long", "double", "bool", "char", "nullable-int"):
        return _read_tokens(cnt, item)
    return [_read(item) for _ in range(cnt)]
def _text(v, t=""):
    if v is None:
        return "-1" if t == "NodeIndex" else "null"
    if isinstance(v, bool):
        return "true" if v else "false"
    return str(v)
def _format(v, t):
    item = _item(t)
    if item:
        if item in ("int", "long", "double", "bool", "char", "nullable-int"):
            return [str(len(v))] + ([" ".join(_text(x) for x in v)] if v else [])
        return [str(len(v))] + [line for x in v for line in _format(x, item)]
    return [_text(v, t)]

if __name__ == "__main__":
    args = [_read(t) for t in _SIG["params"]]
    res = Solution().longestPalindrome(*args)
    ans = res
    sys.stdout.write("\n".join(_format(ans, "string")) + "\n")
