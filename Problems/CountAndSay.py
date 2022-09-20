# The count-and-say sequence is a sequence of digit strings defined by the recursive formula:

# countAndSay(1) = "1"
# countAndSay(n) is the way you would "say" the digit string from countAndSay(n-1), 
# which is then converted into a different digit string.
# To determine how you "say" a digit string, split it into the minimal number of substrings 
# such that each substring contains exactly one unique digit. Then for each substring, say the number of digits, 
# then say the digit. Finally, concatenate every said digit.

# For example, the saying and conversion for digit string "3322251":

#Given a positive integer n, return the nth term of the count-and-say sequence.
import itertools
import re


def countAndSay(n: int) -> str:
    res = "1"
    
    def makeCountAndSay(res: str) -> str:
        index = 0
        count = 1
        newRes = ""
        while index < len(res):
            if index == len(res) - 1:
                newRes += str(count)
                newRes += res[index]
                break
            
            if res[index + 1] != res[index]:
                newRes += str(count)
                newRes += res[index]
                count = 1
                index += 1
                continue
            count += 1
            index += 1
        return newRes
    
    for i in range(n - 1):
        res = makeCountAndSay(res)
    
    return res

def countAndSay2(n: int) -> str:
    s = '1'
    for _ in range(n - 1):
        s = ''.join(str(len(list(group))) + digit
                    for digit, group in itertools.groupby(s))
    return s

def countAndSay3(n):
    s = '1'
    for _ in range(n - 1):
        s = re.sub(r'(.)\1*', lambda m: str(len(m.group(0))) + m.group(1), s)
    return s

print(countAndSay2(4))