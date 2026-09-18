class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, -1);

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1) {
                first[c] = i;
            }
            last[c] = i;
        }

        List<int[]> candidates = new ArrayList<>();

        // A valid substring must begin at the first occurrence of its first character.
        for (int l = 0; l < n; l++) {
            int c = s.charAt(l) - 'a';
            if (first[c] != l) {
                continue;
            }

            int r = last[c];
            boolean valid = true;

            // Expand the interval until every character inside it has
            // all its occurrences inside the interval.
            for (int i = l; i <= r; i++) {
                int d = s.charAt(i) - 'a';

                // The interval would omit a previous occurrence of d.
                if (first[d] < l) {
                    valid = false;
                    break;
                }

                r = Math.max(r, last[d]);
            }

            if (valid) {
                candidates.add(new int[]{l, r});
            }
        }

        // Earliest-ending-first gives the maximum number of intervals.
        // Among maximum-count answers, it also minimizes total length.
        candidates.sort(Comparator.comparingInt(a -> a[1]));

        List<String> result = new ArrayList<>();
        int previousEnd = -1;

        for (int[] interval : candidates) {
            if (interval[0] > previousEnd) {
                result.add(s.substring(interval[0], interval[1] + 1));
                previousEnd = interval[1];
            }
        }

        return result;
    }
}