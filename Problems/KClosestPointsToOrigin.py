import heapq


def kClosest(points, k):
    """
    :type points: List[List[int]]
    :type k: int
    :rtype: List[List[int]]
    """
    for point in points:
        point.append(pow(point[0], 2) + pow(point[1], 2))

    points = sorted(points, key=lambda x: x[2])

    for point in points:
        point.pop()

    return points[0:k][0:1]


def kClosest2(points, k):
    """
    :type points: List[List[int]]
    :type k: int
    :rtype: List[List[int]]
    """
    heap = []
    for point in points:
        dist = point[0] * point[0] + point[1] * point[1]
        heapq.heappush(heap, (-dist, point))
        if len(heap) > k:
            heapq.heappop(heap)

    return [tuple[1] for tuple in heap]


points = [[1, 3], [-2, 2]]
k = 1
print(kClosest(points, k))
