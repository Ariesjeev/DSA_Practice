class Solution:
    def scoreOfParentheses(self, s: str) -> int:
        stack = [0]

        for ch in s:
            if ch == '(':
                stack.append(0)
            else:
                top = stack.pop()
                a = max(2 * top, 1)
                b = stack.pop()
                stack.append(a + b)

        return stack.pop()