from collections import defaultdict
from typing import List


class WordDistance:

    def __init__(self, wordsDict: List[str]):
        self.dic = defaultdict()
        for i, word in enumerate(wordsDict):
            if word in self.dic:
                self.dic[word].append(i)
            else:
                self.dic[word] = []
                self.dic[word].append(i)

    def shortest(self, word1: str, word2: str) -> int:
        list1 = self.dic[word1]
        list2 = self.dic[word2]
        res = sys.maxsize
        for index1 in list1:
            for index2 in list2:
                res = min(res, abs(index1 - index2))
        return res


# Your WordDistance object will be instantiated and called as such:
# obj = WordDistance(wordsDict)
# param_1 = obj.shortest(word1,word2)
