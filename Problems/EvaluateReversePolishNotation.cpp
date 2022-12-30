#include <functional>
#include <iostream>
#include <stack>
#include <unordered_map>
#include <vector>
using namespace std;

class Solution {
   public:
    bool validOperators(string a) {
        return a == "+" || a == "-" || a == "*" || a == "/";
    }

    int calculate(int a, int b, string c) {
        if (c == "+") {
            return a + b;
        } else if (c == "-") {
            return a - b;
        } else if (c == "*") {
            return a * b;
        } else {
            return a / b;
        }
    }

    int evalRPN(vector<string>& tokens) {
        if (tokens.size() == 1)
            return stoi(tokens[0]);
        stack<int> stack;
        for (auto& token : tokens) {
            if (!validOperators(token)) {
                stack.push(stoi(token));
                continue;
            }
            int b = stack.top();
            stack.pop();
            int a = stack.top();
            stack.pop();
            stack.push(calculate(a, b, token));
        }
        return stack.top();
    }

    // another solution using unordered_map
    // function can be stored in a map!
    int evalRPN2(vector<string>& tokens) {
        stack<int> s;
        unordered_map<string, function<int(int, int)>> map{
            {"+", plus<int>()},
            {"-", minus<int>()},
            {"*", multiplies<int>()},
            {"/", divides<int>()}};

        for (const auto& token : tokens) {
            const auto& op = map.find(token);
            if (op != map.end()) {
                int rhs = s.top();
                s.pop();
                int lhs = s.top();
                s.pop();
                s.push((*op).second(lhs, rhs));
            } else {
                s.push(stoi(token));
            }
        }
        return s.top();
    }
};