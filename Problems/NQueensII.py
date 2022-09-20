#The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

#Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.

#Each solution contains a distinct board configuration of the n-queens' placement, 
# where 'Q' and '.' both indicate a queen and an empty space, respectively.

def totalNQueens(n: int) -> int:
    col = set()
    posDiag = set()
    negDiag = set()
    
    res = 0
    def backtrack(r):
        if r == n:
            nonlocal res
            res += 1
            return
        
        for c in range(n):
            if c in col or (r + c) in posDiag or (r - c) in negDiag:
                continue
            col.add(c)
            posDiag.add(r + c)
            negDiag.add(r - c)
            backtrack(r + 1)
            col.remove(c)
            posDiag.remove(r + c)
            negDiag.remove(r - c)
            
    backtrack(0)
    return res

print(totalNQueens(4))