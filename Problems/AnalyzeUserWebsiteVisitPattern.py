import collections
import itertools
from typing import List


def mostVisitedPattern(self, username, timestamp, website):
    dp = collections.defaultdict(list)
    for t, u, w in sorted(zip(timestamp, username, website)):
        dp[u].append(w)
    count = sum([collections.Counter(set(itertools.combinations(dp[u], 3)))
                for u in dp], collections.Counter())
    return list(min(count, key=lambda k: (-count[k], k)))


def mostVisitedPattern2(self, username: List[str], timestamp: List[int], website: List[str]) -> List[str]:

    # ---> [(3, 'joe', 'career'),....]
    packed_tuple = zip(timestamp, username, website)
    # sort by timestamp, as it didn't say timestamp is sorted array
    sorted_packed_tuple = sorted(packed_tuple)
    # By default tuple always being sorted by the first item

    mapping = collections.defaultdict(list)
    # joe: ['home', 'about', 'career']  websites in list are in ascending timestamp order
    for t, u, w in sorted_packed_tuple:
        mapping[u].append(w)

    counter_dict = collections.defaultdict(
        int)         # use a dict for counting
    for website_list in mapping.values():
        # size of combination is set to 3, for details see bottom
        combs = set(itertools.combinations(website_list, 3))
        for comb in combs:
            # Tuple as key, counter as value,  e.g. ('home', 'about', 'career') : 2
            counter_dict[comb] += 1

    # sort descending by value, then lexicographically
    sorted_counter_dict = sorted(
        counter_dict, key=lambda x: (-counter_dict[x], x))
    return sorted_counter_dict[0]
