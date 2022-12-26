import heapq


class newMap:
    def __init__(self) -> None:
        self.nMap = {}

    def insert(self, x, y) -> None:
        self.nMap[x] = y

    def get(self, x) -> int:
        return self.nMap[x]

    def addToKey(self, x) -> None:
        newMap = {}
        for key, val in self.nMap.items():
            newMap[key + x] = val
        self.nMap = newMap

    def addToValue(self, y) -> None:
        for key, val in self.nMap.items():
            self.nMap[key] = val + y


nMap = newMap()
newMap.insert(nMap, 1, 2)
newMap.insert(nMap, 2, 3)
newMap.addToValue(nMap, 2)
newMap.addToKey(nMap, 1)
print(newMap.get(nMap, 3))


def trie():
    arr = ["back", "backdoor", "background",
           "ground", "yard", "backyard", "groundwater"]
    res, trie = 0, dict()
    for word in sorted(arr, key=len):
        cur = trie
        for w in word:
            cur = cur.setdefault(w, dict())
            res += cur.get('#', 0)
        cur['#'] = 1
    return res


print(trie())


def vertTrav(i, j, arr, heap, sOrl, cache) -> int:
    if j < 0:
        return 0
    res = 0
    while i < len(arr):
        if arr[i][j] == sOrl or arr[i][j] == "#":
            break
        arr[i][j] = sOrl
        heapq.heappush(heap, (j, i))
        if cache[i] > j:
            cache[i] = j
        res += 1
        i += 1
    return res


def rboard():
    arr = [[".", ".", ".", "#", "."],
           [".", "#", ".", "#", "#"],
           [".", ".", ".", ".", "."],
           [".", "#", ".", "#", "#"]]

    # arr = [[".", "#", "#"],
    #        [".", ".", "#"],
    #        [".", ".", "."]]

    sCache = [0] * len(arr)
    lCache = [0] * len(arr)
    minHeap = []
    maxHeap = []
    for i in range(len(arr)):
        for j in range(len(arr[i])):
            if arr[i][j] == "#":
                heapq.heappush(minHeap, (j, i))
                heapq.heappush(maxHeap, (j, i))
                sCache[i] = j
                lCache[i] = j
                break
            if j == len(arr[i]) - 1:
                heapq.heappush(minHeap, (j+1, i))
                heapq.heappush(maxHeap, (j+1, i))
                sCache[i] = j+1
                lCache[i] = j+1

    short = 0
    index = 0
    while len(minHeap) > 0:
        tup = heapq.heappop(minHeap)
        if sCache[tup[1]] != tup[0]:
            continue
        short += vertTrav(tup[1], tup[0] - 1, arr, minHeap, "s", sCache)
        index += 1

    long = 0
    index = 0
    while len(maxHeap) > 0:
        heapq._heapify_max(maxHeap)
        tup = heapq._heappop_max(maxHeap)
        if lCache[tup[1]] != tup[0]:
            continue
        long += vertTrav(tup[1], tup[0] - 1, arr, maxHeap, "l", lCache)
        index += 1
    return (short, long)


print(rboard())
