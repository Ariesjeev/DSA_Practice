class Solution:
    def maxAlternatingSum(self, nums: List[int]) -> int:
        n = len(nums)
        # Step 1: Calculate the square of each number
        squares = [x * x for x in nums]
        # Step 2: Sort squares in ascending order
        squares.sort()

        score = 0

        # Step 3: Subtract the smallest n//2 squares
        for i in range(n // 2):
            score -= squares[i]

        # Step 4: Add the remaining squares
        for i in range(n // 2, n):
            score += squares[i]

        # Step 5: Return the maximum score
        return score
