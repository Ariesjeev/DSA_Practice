class Solution:
    def maxSumOfSquares(self, num: int, sum: int) -> str:
        if sum > num * 9:
            return ""
        ans = []
        for _ in range(num):
            if sum >= 9:
                ans.append("9")
                sum -= 9
            else:
                ans.append(str(sum))
                sum = 0
        return "".join(ans)