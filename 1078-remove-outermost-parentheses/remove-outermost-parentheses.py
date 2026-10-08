class Solution:
    def removeOuterParentheses(self, s: str) -> str:
        answer = []
        depth = 0

        for ch in s:
            # Opening bracket
            if ch == '(':
                # Already inside, so not the outermost bracket.
                if depth != 0:
                    answer.append('(')
                depth += 1
            # Closing bracket
            else:
                depth -= 1
                # Still inside, so not the outermost bracket.
                if depth != 0:
                    answer.append(')')

        return ''.join(answer)