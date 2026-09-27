class Solution:
    def reverseParentheses(self, s: str) -> str:
        stack = []
        current = ""

        for ch in s:
            if ch == '(':
                stack.append(current)
                current = ""
            elif ch == ')':
                current = current[::-1]
                previous = stack.pop()
                current = previous + current
            else:
                current = current + ch

        return current
        