from functools import cache


def numTilings(n: int) -> int:
    # f(i) = f(i - 1) + f(i - 2) + 2 * p(i - 1)
    # p(i) = f(i - 2) + p(i - 1)
    MOD = 1_000_000_007

    @cache
    def p(n):
        if n == 2:
            return 1
        return (p(n - 1) + f(n - 2)) % MOD

    @cache
    def f(n):
        if n <= 2:
            return n
        return (f(n - 1) + f(n - 2) + 2 * p(n - 1)) % MOD

    return f(n)


print(numTilings(5))
