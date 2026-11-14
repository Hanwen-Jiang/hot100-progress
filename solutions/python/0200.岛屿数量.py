from typing import List


class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        h, w = len(grid), len(grid[0])
        num = 0

        def delLand(x, y):
            if x < 0 or y < 0 or x >= h or y >= w:
                return
            sea = grid[x][y] == '0'
            if not sea:
                grid[x][y] = '0'
                top, bottom, left, right = (x-1, y), (x+1, y), (x, y-1), (x, y+1)
                delLand(*top)
                delLand(*bottom)
                delLand(*left)
                delLand(*right)

        for i, row in enumerate(grid):
            for j, cell in enumerate(row):
                if cell == '1':
                    delLand(i, j)
                    num = num + 1
        return num
