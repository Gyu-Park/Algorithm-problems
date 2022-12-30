#include <iostream>
#include <stack>
using namespace std;

class MyQueue {
   public:
    MyQueue() {}

    void push(int x) {
        pushStack.push(x);
    }

    int pop() {
        int ret;
        if (popStack.empty())
            fillpopStack();
        ret = popStack.top();
        popStack.pop();
        return ret;
    }

    int peek() {
        if (popStack.empty())
            fillpopStack();
        return popStack.top();
    }

    bool empty() {
        return popStack.empty() && pushStack.empty();
    }

   private:
    stack<int> pushStack;
    stack<int> popStack;
    void fillpopStack() {
        while (!pushStack.empty()) {
            popStack.push(pushStack.top());
            pushStack.pop();
        }
    }
};

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue* obj = new MyQueue();
 * obj->push(x);
 * int param_2 = obj->pop();
 * int param_3 = obj->peek();
 * bool param_4 = obj->empty();
 */