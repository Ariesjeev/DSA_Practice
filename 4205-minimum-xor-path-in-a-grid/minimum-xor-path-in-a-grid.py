class Solution:
    def minCost(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        
        # dp[c] will store a set of all possible XOR values to reach column c in the current row
        dp = [set() for _ in range(n)]
        dp[0].add(grid[0][0])
        
        for r in range(m):
            next_dp = [set() for _ in range(n)]
            for c in range(n):
                incoming_xors = set()
                
                # Gather incoming values from the top cell
                if r > 0:
                    for val in dp[c]:
                        incoming_xors.add(val)
                
                # Gather incoming values from the left cell
                if c > 0:
                    for val in next_dp[c-1]:
                        incoming_xors.add(val)
                
                # Apply current grid value via XOR for all valid paths
                # for val in incoming_xors:
                if r == 0 and c == 0:
                    next_dp[c].add(grid[0][0])
                else:
                    for val in incoming_xors:
                        next_dp[c].add(val ^ grid[r][c])
            
            dp = next_dp
            
        # The result is the minimum XOR value found at the bottom-right cell
        return min(dp[n - 1])