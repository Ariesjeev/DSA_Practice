class Solution:
    def minOperations(self, nums: list[int], x: int) -> int:
        n=len(nums)
        total=sum(nums)
        target=total-x
        start=end=0
        max_len=-1
        res=0
        if target < 0:
            return -1
        while end<n:
            res+=nums[end]
            while res>target :
                res-=nums[start]
                start+=1
            if res==target:
                max_len=max(max_len,end-start+1)
            end+=1
        if max_len==-1:
            return -1
        return n-max_len