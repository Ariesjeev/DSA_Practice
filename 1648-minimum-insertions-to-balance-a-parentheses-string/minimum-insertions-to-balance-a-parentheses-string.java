class Solution {
    public int minInsertions(String s) {
        int open = 0;
        int insertions = 0;


        for (int i = 0; i < s.length(); i++) {


            if (s.charAt(i) == '(') {
                open++;
            } else {


                // If the next character is not ')',
                // insert one ')' to complete the pair.
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++;
                } else {
                    insertions++;
                }


                // Match the closing pair with an opening bracket.
                if (open > 0) {
                    open--;
                } else {
                    // No opening bracket exists; insert '('.
                    insertions++;
                }
            }
        }


        // Each remaining '(' needs two ')'.
        insertions += open * 2;


        return insertions;
    }
}