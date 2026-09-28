class Solution:
    def checkPerfectNumber(self, num: int) -> bool:
        if num <= 1:
            return False
            
        sum_divisors = 1  # 1 is always a divisor for num > 1
        i = 2
        while i * i <= num:
            if num % i == 0:
                sum_divisors += i
                if i * i != num:
                    sum_divisors += num // i
            i += 1
            
        return sum_divisors == num
        