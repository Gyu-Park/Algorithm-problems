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


def numTilings(N):
    # A[i] = 2 * (N-1) board
    # B[i] = 2 * N board.
    A = [0] * (N + 1)
    B = [1, 1] + [0] * (N - 1)
    for i in range(2, N + 1):
        A[i] = (B[i - 2] + A[i - 1]) % int(1e9 + 7)
        B[i] = (B[i - 1] + B[i - 2] + A[i - 1] * 2) % int(1e9 + 7)
    return B[N]


print(numTilings(5))
