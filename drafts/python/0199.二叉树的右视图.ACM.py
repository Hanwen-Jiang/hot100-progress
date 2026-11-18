n = int(input())
datas = list(input())
form collections import deque

class Tree:
    def __init__(self,d=None, l=None, r=None):
        self.left, self.right = l, r
        self.data = d
        
def build():
    k, ind = deque([datas[0]]), 0
    while len(k) != 0:
