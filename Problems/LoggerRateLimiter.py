class Logger:

    def __init__(self):
        self.map = {}

    def shouldPrintMessage(self, timestamp: int, message: str) -> bool:
        if message not in self.map or self.map[message] <= timestamp:
            self.map[message] = timestamp + 10
            return True
        return False
