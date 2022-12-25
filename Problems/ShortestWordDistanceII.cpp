#include <iostream>
#include <vector>
#include <unordered_map>
using namespace std;

class WordDistance
{
public:
    WordDistance(vector<string> &wordsDict)
    {
        for (int i = 0; i < wordsDict.size(); i++)
            this->umap[wordsDict[i]].push_back(i);
    }

    int shortest(string word1, string word2)
    {
        vector<int> list1 = this->umap[word1];
        vector<int> list2 = this->umap[word2];
        int res = INT_MAX, i = 0, j = 0;
        while (i < list1.size() && j < list2.size())
        {
            res = min(res, abs(list1[i] - list2[j]));
            list1[i] < list2[j] ? i++ : j++;
        }
        return res;
    }

private:
    unordered_map<string, vector<int>> umap;
};

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance* obj = new WordDistance(wordsDict);
 * int param_1 = obj->shortest(word1,word2);
 */