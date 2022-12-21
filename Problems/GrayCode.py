def grayCode(n):
    if n == 0:
        return [0]
    nMax = 2**n
    return [(x >> 1) ^ x for x in range(nMax)]


print(grayCode(4))

# (0 >> 1) ^ 0 = 000
# (1 >> 1) ^ 1 = 001
# (2 >> 1) ^ 2 = 01 ^ 10 = 011
# (3 >> 1) ^ 3 = 01 ^ 11 = 010
# (4 >> 1) ^ 4 = 010 ^ 100 = 110
# (5 >> 1) ^ 5 = 010 ^ 101 = 111
