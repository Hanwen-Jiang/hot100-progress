n = int(input())
data = list(input())

class Tree:
    def __init__(self, l=None, r=None):
        self.left, self.right = l, r
