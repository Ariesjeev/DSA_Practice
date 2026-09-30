class Solution:
    def maxDepthAfterSplit(self, seq: str) -> list[int]:
        answer = [0] * len(seq)
        depth = 0

        for i, ch in enumerate(seq):
            if ch == '(':
                depth += 1
                answer[i] = depth % 2
            else:
                answer[i] = depth % 2
                depth -= 1

        return answer