from typing import List


class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        m, n = len(grid), len(grid[0])

        # 淹掉一座新岛返回 1，碰到水或越界返回 0，答案就是所有返回值之和
        def sink(i, j):
            if 0 <= i < m and 0 <= j < n and grid[i][j] == '1':
                grid[i][j] = '0'
                for di, dj in (1, 0), (-1, 0), (0, 1), (0, -1):
                    sink(i + di, j + dj)
                return 1
            return 0

        return sum(sink(i, j) for i in range(m) for j in range(n))
