__author__ = 'BeyondSky'

from collections import defaultdict

class Solution(object):
    def getHint(self, secret, guess):
        """
        :type secret: str
        :type guess: str
        :rtype: str
        """
        bulls = cows = 0
        digits = defaultdict(int)
        # first pass: count bulls and non-matching digits
        for index in range(len(secret)):
            if secret[index] == guess[index]:
                # matches, count the number of bulls
                bulls += 1
            else:
                # not match, increase number of non-matching digits
                digits[secret[index]] += 1

        # second pass: count number of cows
        for index in range(len(secret)):
            if secret[index] != guess[index]:
                # decrease number of non-matching digit by 1 if it is greater than 0
                if digits[guess[index]] > 0:
                    cows += 1
                    digits[guess[index]] -= 1

        return str(bulls) + 'A' + str(cows) + 'B'