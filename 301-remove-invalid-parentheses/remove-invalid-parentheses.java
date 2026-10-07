class Solution {

    public List<String> removeInvalidParentheses(String s) {

        List<String> answer = new ArrayList<>();

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(s);
        visited.add(s);

        boolean found = false;

        while (!queue.isEmpty()) {

            int size = queue.size();

            // Process only the current BFS level
            for (int i = 0; i < size; i++) {

                String current = queue.poll();

                // Check whether current string is valid
                if (isValid(current)) {
                    answer.add(current);
                    found = true;
                }

                // If valid strings are found,
                // don't generate the next level
                if (found) {
                    continue;
                }

                // Remove one parenthesis
                for (int j = 0; j < current.length(); j++) {

                    char ch = current.charAt(j);

                    // We can remove only parentheses
                    if (ch != '(' && ch != ')') {
                        continue;
                    }

                    String next =
                            current.substring(0, j)
                            + current.substring(j + 1);

                    // Avoid duplicate strings
                    if (!visited.contains(next)) {
                        visited.add(next);
                        queue.offer(next);
                    }
                }
            }

            // First valid level = minimum removals
            if (found) {
                break;
            }
        }

        return answer;
    }

    private boolean isValid(String s) {

        int count = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                count++;
            }
            else if (ch == ')') {
                count--;

                // More closing brackets than opening brackets
                if (count < 0) {
                    return false;
                }
            }
        }

        // All opening brackets must also be matched
        return count == 0;
    }
}