__author__ = 'BeyondSky'



class Solution(object):
    def wordPattern(self, pattern, str):
        """
        :type pattern: str
        :type str: str
        :rtype: bool
        """

        if str is None:
            return False

        words = str.split()
        if len(pattern) != len(words):
            return False

        l = len(pattern);
        dict1 = {}
        dict2 = {}
        for i in range(l):
            c = pattern[i]
            w = words[i]
            if c not in dict1:
                if w in dict2:
                    return False

                dict1[c] = w
                dict2[w] = c
            else:
                if dict1[c] != w:
                    return False
        return True


if __name__ == "__main__":
    outer = Solution()
    p = "abba"
    s = "dog cat cat dog"
    print(outer.wordPattern(p, s))



