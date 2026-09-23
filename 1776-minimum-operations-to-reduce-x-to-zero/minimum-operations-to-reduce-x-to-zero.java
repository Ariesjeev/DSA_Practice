class Solution {
    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        
        int target = total - x;
        if (target < 0) {
            return -1;
        }
        
        int start = 0, end = 0, maxLen = -1;
        int res = 0;
        
        while (end < n) {
            res += nums[end];
            
            while (res > target && start <= end) {
                res -= nums[start];
                start++;
            }
            
            if (res == target) {
                maxLen = Math.max(maxLen, end - start + 1);
            }
            end++;
        }
        
        if (maxLen == -1) {
            return -1;
        }
        return n - maxLen;
    }
}