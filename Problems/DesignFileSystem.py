class FileSystem:

    def __init__(self):
        self.dic = {}

    def createPath(self, path: str, value: int) -> bool:
        if path == "" or path == "/" or path[0] != '/' or path in self.dic:
            return False
        dir = path.split("/")
        st = ""

        # path[:path.rfind('/')] with this line of code, can find the last occurence(index) of '/'

        for d in dir[1:-1]:
            st += "/" + d
            if len(dir) > 2 and st not in self.dic:
                return False
        self.dic[path] = value
        return True

    def get(self, path: str) -> int:
        if path in self.dic:
            return self.dic[path]
        return -1


# Your FileSystem object will be instantiated and called as such:
# obj = FileSystem()
# param_1 = obj.createPath(path,value)
# param_2 = obj.get(path)
class FileSystem2:

    def __init__(self):
        self.d = {"": -1}

    def create(self, path: str, value: int) -> bool:
        parent = path[:path.rfind('/')]
        if parent in self.d and path not in self.d:
            self.d[path] = value
            return True
        return False

    def get(self, path: str) -> int:
        return self.d.get(path, -1)
