class Solution:
    def countDaysTogether(
        self, arriveAlice: str, leaveAlice: str, arriveBob: str, leaveBob: str
    ) -> int:

        def getDays(date: str) -> int:
            days = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
            month = int(date[:2])
            day = int(date[3:])
            total = day
            for i in range(month - 1):
                total += days[i]
            return total

        startAlice = getDays(arriveAlice)
        endAlice = getDays(leaveAlice)
        startBob = getDays(arriveBob)
        endBob = getDays(leaveBob)

        start = max(startAlice, startBob)
        end = min(endAlice, endBob)

        if start > end:
            return 0
        return end - start + 1
