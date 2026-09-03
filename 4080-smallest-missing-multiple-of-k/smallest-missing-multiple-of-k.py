class Solution(object):
    def missingMultiple(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: int
        """
        present = set(nums)

        multiple = k

        while multiple in present:
            multiple += k
        return multiple        