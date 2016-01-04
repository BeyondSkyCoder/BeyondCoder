__author__ = 'BeyondSky'

import re

class Solution:
    # @param {string} s
    # @return {integer}
    def calculate_I(self, s):
        tokens = self.toRPN(s)
        return self.evalRPN(tokens)

    operators = ['+', '-', '*', '/']

    def toRPN(self, s):
        tokens, stack = [], []
        number = ''
        for c in s:
            if c.isdigit():
                number += c
            else:
                if number:
                    tokens.append(number)
                    number = ''
                if c in self.operators:
                    while len(stack) and self.getPriority(stack[-1]) >= self.getPriority(c):
                        tokens.append(stack.pop())
                    stack.append(c)
                elif c == '(':
                    stack.append(c)
                elif c == ')':
                    while len(stack) and stack[-1] != '(':
                        tokens.append(stack.pop())
                    stack.pop()
        if number:
            tokens.append(number)
        while len(stack):
            tokens.append(stack.pop())
        return tokens

    def evalRPN(self, tokens):
        stack = []
        for token in tokens:
            if token in self.operators:
                y, x = stack.pop(), stack.pop()
                stack.append(self.getVal(x, y, token))
            else:
                stack.append(int(token))
        return stack[0]

    def getVal(self, x, y, operator):
        return {
            '+': lambda x, y: x + y,
            '-': lambda x, y: x - y,
            '*': lambda x, y: x * y,
            '/': lambda x, y: int(float(x) / y),
        }[operator](x, y)

    def getPriority(self, operator):
        return {
            '+' : 1,
            '-' : 1,
            '*' : 2,
            '/' : 2,
        }.get(operator, 0)


    # @param {string} s
    # @return {integer}
    def calculate_II(self, s):
        op = {'+': lambda x, y: x + y, '-': lambda x, y: x - y, '*': lambda x, y: x * y, '/': lambda x, y: int(float(x) / y)}

        s = re.sub(r'\d+', ' \g<0> ', s)
        expression = s.split()
        total = d = idx = 0

        func = op['+']
        while idx < len(expression):
            token = expression[idx]
            if token in '+-':
                total = func(total, d)
                func = op[token]
            elif token in '*/':
                idx += 1
                d = op[token](d, int(expression[idx]))
            else:
                d = int(token)
            idx += 1
        return func(total, d)
    

if __name__ == "__main__":
    outer = Solution()
    print(outer.calculate_II("1-1+1*2"))