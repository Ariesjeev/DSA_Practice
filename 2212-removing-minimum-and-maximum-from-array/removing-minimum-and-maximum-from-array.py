class Solution(object):
    def minimumDeletions(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        n = len(nums)
        Max = nums[0]
        Min = nums[0]
        max_i = 0
        min_i = 0

        for i in range(1,n):
            if(nums[i]>Max):
                Max = nums[i]
                max_i = i
            
            if(nums[i]<Min):
                Min = nums[i]
                min_i = i
        
        left = max(min_i,max_i) + 1
        right = n - min(min_i,max_i)
        both =  min(min_i,max_i) + 1 +  n - max(min_i,max_i)
        
        return min(left,right,both)
        