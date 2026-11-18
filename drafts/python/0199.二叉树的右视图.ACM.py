from collections import deque
import math
import sys
n = int(input())
datas = [None if x=='null' else x for x in input().split()]
res = []
if len(datas) == 0:
    print(0)
    print()
    sys.exit()
print(math.ceil(math.log2(n + 1)))

class Tree:
    def __init__(self,d=None, l=None, r=None):
        self.left, self.right = l, r
        self.data = d
        
def build():
    if len(datas) == 0:
        return None
    tree = Tree(datas[0])
    k, dq = deque([tree]), deque(datas[1:])
    while len(k) != 0:
        y = deque([])
        while len(k) != 0:
            x = k.popleft()
            y1, y2 = Tree(dq.popleft()) if len(dq)!=0 else None, Tree(dq.popleft()) if len(dq)!=0 else None
            x.left, x.right = y1, y2
            y.append(y1)
            y.append(y2)
        k=y
    return tree

tree = build()
dq1 = deque([tree])
print(tree.data)
res.append(tree.data)
while len(dq1) != 0:
    dq2 = deque([])
    while len(dq1) != 0:
        x=dq1.popleft()
        dq2.append(x.left)
        dq2.append(x.right)
    res.append(dq2[-1].data)
    print(dq2[-1].data)
    dq1=dq2
