class Solution(object):
    def checkDivisibility(self, n):
        """
        :type n: int
        :rtype: bool
        """
         # 123456/10 = 12345
        # 123456%10 = 6
        num = n
        sum = 0
        product = 1

        while(num > 0):
            sum +=num % 10
            product *= num %10

            num = num/10
            
        if(n % (sum + product) == 0):
            return True
        return False

        