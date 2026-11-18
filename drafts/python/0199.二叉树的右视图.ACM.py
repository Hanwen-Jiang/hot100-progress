from collections import deque
n = int(input())
datas = [None if x=='null' else x for x in input().split()]
res = []

class Tree:
    def __init__(self,d=None, l=None, r=None):
        self.left, self.right = l, r
        self.data = d
        
def build():
    tree = Tree(datas[0])
    k, dq = deque([tree]), deque(datas[1:])
    while len(k) != 0:
        y = deque([])
        while len(k) != 0:
            x = k.popleft()
            y1, y2 = Tree(dq.popleft()), Tree(dq.popleft())
            x.left, x.right = y1, y2
            y.append(y1)
            y.append(y2)
        k=y
    return tree

build()
