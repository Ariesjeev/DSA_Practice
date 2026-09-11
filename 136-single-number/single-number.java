class Solution {
    public int singleNumber(int[] nums) {
        //   int n = nums.length;

        // // Step 1: Find maximum element
        // int maxi = nums[0];
        // for (int i = 0; i < n; i++) {
        //     maxi = Math.max(maxi, nums[i]);
        // }

        // // Step 2: Create frequency array of size maxi+1
        // int[] hash = new int[maxi + 1];

        // // Step 3: Count frequencies
        // for (int i = 0; i < n; i++) {
        //     hash[nums[i]]++;
        // }

        // // Step 4: Find element with frequency = 1
        // for (int i = 0; i < n; i++) {
        //     if (hash[nums[i]] == 1)
        //         return nums[i];
        // }

        // return -1; // fallback
         int result = 0;
        for (int num : nums) {
            // XOR each number with the result
            result ^= num;
        }
        return result;
    }
}