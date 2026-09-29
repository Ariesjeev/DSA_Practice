class Solution {
    public boolean hasValidPath(char[][] grid) {


        int m = grid.length;
        int n = grid[0].length;


        // Total path length must be even
        if ((m + n - 1) % 2 != 0) {
            return false;
        }


        // First character must be '('
        if (grid[0][0] == ')') {
            return false;
        }


        Queue<int[]> queue = new LinkedList<>();


        // {row, col, balance}
        queue.offer(new int[]{0, 0, 1});


        // visited[row][col][balance]
        boolean[][][] visited = new boolean[m][n][m + n];


        visited[0][0][1] = true;


        int[][] directions = {
            {1, 0},  // down
            {0, 1}   // right
        };


        while (!queue.isEmpty()) {


            int[] current = queue.poll();


            int row = current[0];
            int col = current[1];
            int balance = current[2];


            // Reached destination
            if (row == m - 1 && col == n - 1) {
                if (balance == 0) {
                    return true;
                }
            }


            for (int[] dir : directions) {


                int newRow = row + dir[0];
                int newCol = col + dir[1];


                // Out of bounds
                if (newRow >= m || newCol >= n) {
                    continue;
                }


                int newBalance = balance;


                if (grid[newRow][newCol] == '(') {
                    newBalance++;
                } else {
                    newBalance--;
                }


                // Invalid prefix
                if (newBalance < 0) {
                    continue;
                }


                // Already visited same state
                if (visited[newRow][newCol][newBalance]) {
                    continue;
                }


                visited[newRow][newCol][newBalance] = true;


                queue.offer(new int[]{
                    newRow,
                    newCol,
                    newBalance
                });
            }
        }


        return false;
    }
}