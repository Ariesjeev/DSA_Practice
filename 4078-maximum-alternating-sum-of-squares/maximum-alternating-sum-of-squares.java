class Solution {
    public long maxAlternatingSum(int[] nums) {
         int n = nums.length;
        long[] squares = new long[n];

        // Step 1: Calculate the square of each number
        for (int i = 0; i < n; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }

        // Step 2: Sort squares in ascending order
        Arrays.sort(squares);

        long score = 0;

        // Step 3: Subtract the smallest n/2 squares
        for (int i = 0; i < n / 2; i++) {
            score -= squares[i];
        }

        // Step 4: Add the remaining squares
        for (int i = n / 2; i < n; i++) {
            score += squares[i];
        }

        // Step 5: Return the maximum score
        return score;
    }
}