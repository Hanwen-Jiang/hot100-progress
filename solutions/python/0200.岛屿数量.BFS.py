from collections import deque
from typing import List


class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        # 用队列代替递归，避免超长的岛触发 Python 递归深度上限
        m, n = len(grid), len(grid[0])
        count = 0
        for i in range(m):
            for j in range(n):
                if grid[i][j] != '1':
                    continue
                count += 1
                grid[i][j] = '0'
                q = deque([(i, j)])
                while q:
                    x, y = q.popleft()
                    for nx, ny in (x + 1, y), (x - 1, y), (x, y + 1), (x, y - 1):
                        # 入队时就做记号，防止同一格被重复入队
                        if 0 <= nx < m and 0 <= ny < n and grid[nx][ny] == '1':
                            grid[nx][ny] = '0'
                            q.append((nx, ny))
        return count
