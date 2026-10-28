class Solution {
   int[][][] dp;

    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        dp = new int[m][n][1024];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return solve(grid, 0, 0, 0);
    }

    int solve(int[][] grid, int row, int col, int xor) {
        int m = grid.length;
        int n = grid[0].length;

        // Invalid path
        if (row >= m || col >= n) {
            return Integer.MAX_VALUE;
        }

        // Current cell ka XOR
        xor ^= grid[row][col];

        // Destination
        if (row == m - 1 && col == n - 1) {
            return xor;
        }

        if (dp[row][col][xor] != -1) {
            return dp[row][col][xor];
        }

        // Dono directions explore karo
        int right = solve(grid, row, col + 1, xor);
        int down = solve(grid, row + 1, col, xor);

        return dp[row][col][xor] = Math.min(right, down);
    }
}