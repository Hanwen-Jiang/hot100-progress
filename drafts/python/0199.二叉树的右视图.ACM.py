from collections import deque
import sys
n = int(input())
if n == 0:
    print(0)
    print()
    sys.exit()
datas = [None if x=='null' else x for x in input().split()]
res = []

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
            d1 = dq.popleft() if len(dq)!=0 else None
            d2 = dq.popleft() if len(dq)!=0 else None
            y1 = Tree(d1) if d1 is not None else None
            y2 = Tree(d2) if d2 is not None else None
            x.left, x.right = y1, y2
            if y1 is not None:
                y.append(y1)
            if y2 is not None:
                y.append(y2)
        k=y
    return tree

tree = build()
dq1 = deque([tree])
res.append(tree.data)
while len(dq1) != 0:
    dq2 = deque([])
    while len(dq1) != 0:
        x=dq1.popleft()
        if x.left is not None:
            dq2.append(x.left)
        if x.right is not None:
            dq2.append(x.right)
    if len(dq2) > 0:
        res.append(dq2[-1].data)
    dq1=dq2
    
print(len(res))
for x in res:
    print(x)
