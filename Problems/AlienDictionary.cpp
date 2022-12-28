#include <iostream>
#include <queue>
#include <unordered_map>
#include <unordered_set>
#include <vector>
using namespace std;

class Solution {
   public:  // solution using Khan's Algorithm (Topological sorting)
    string alienOrder(vector<string>& words) {
        // unordered_map for inDegree - ex) 'a' from 'b'
        // unordered_map for outDegree = ex) 'a' to 'c'
        // if some nodes have no inDegree, then put it into a queue
        // then using Khan's Algorithm until queue is empty
        // if a node of inDegree that is not empty exists, then there is a cycle
        // otherwise, return the res vector
        unordered_set<char> charSet;
        unordered_map<char, int> inDegree;
        unordered_map<char, vector<char>> outDegree;
        for (int i = 0; i < words.size(); i++) {
            for (char c : words[i]) {
                if (charSet.find(c) == charSet.end()) {
                    charSet.insert(c);
                    inDegree[c] = 0;
                }
            }
        }
        for (int i = 1; i < words.size(); i++) {
            auto word1 = words[i - 1];
            auto word2 = words[i];
            int j = 0;
            while (word1[j] == word2[j]) j++;
            if (j < word1.size() && j >= word2.size())
                return "";
            if (j < word1.size() && j < word2.size()) {
                outDegree[word1[j]].push_back(word2[j]);
                inDegree[word2[j]]++;
            }
        }
        queue<char> q;
        for (const auto& [key, value] : inDegree) {
            if (value == 0)
                q.push(key);
        }
        string res = "";
        while (!q.empty()) {
            char c = q.front();
            q.pop();
            res += c;
            vector<char> v = outDegree[c];
            for (char a : v) {
                inDegree[a]--;
                if (inDegree[a] == 0)
                    q.push(a);
            }
        }
        return res.size() == charSet.size() ? res : "";
    }
};