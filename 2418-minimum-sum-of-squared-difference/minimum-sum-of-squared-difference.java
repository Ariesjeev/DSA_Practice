class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int[] freq = new int[100001];
        int maxDiff = 0;
        long totalDiff = 0;

        // Step 1: Calculate differences and frequencies
        for (int i = 0; i < nums1.length; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            freq[diff]++;
            totalDiff += diff;
            maxDiff = Math.max(maxDiff, diff);
        }

        // Step 2: Combine operations
        long k = (long) k1 + k2;

        // Step 3: Check if all differences can become zero
        if (totalDiff <= k) {
            return 0;
        }

        // Step 4: Reduce the largest differences first
        for (int d = maxDiff; d > 0 && k > 0; d--) {
            int moves = (int) Math.min(k, (long) freq[d]);
            freq[d] -= moves;
            freq[d - 1] += moves;
            k -= moves;
        }

        // Step 5: Calculate the final squared sum
        long answer = 0;
        for (int d = 1; d <= maxDiff; d++) {
            answer += (long) d * d * freq[d];
        }
        return answer;
    }
}