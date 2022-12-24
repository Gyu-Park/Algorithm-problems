#include <iostream>
#include <vector>
#include <unordered_map>
using namespace std;

// a solution usign Trie data structure
struct Trie
{
public:
    int val;
    unordered_map<string, struct Trie *> umap;
    Trie(int val)
    {
        this->val = val;
    }
};
class FileSystem
{
private:
    vector<string> split(string &path)
    {
        vector<string> result;
        string cur = "";
        for (int i = 1; i < path.size(); ++i)
        {
            char c = path[i];
            if (c == '/')
            {
                result.push_back(cur);
                cur = "";
            }
            else
            {
                cur.push_back(c);
            }
        }
        result.push_back(cur);
        return result;
    }

public:
    struct Trie *roots;
    FileSystem()
    {
        this->roots = new Trie(-1);
    }

    bool createPath(string path, int value)
    {
        if (path.size() <= 1 && path[0] != '/')
            return false;
        vector<string> tokens = this->split(path);
        struct Trie *t = roots;
        for (int i = 0; i < tokens.size() - 1; i++)
        {
            string token = tokens[i];
            if (!t->umap[token])
                return false;
            t = t->umap[token];
        }
        if (t->umap[tokens[tokens.size() - 1]])
            return false;
        t->umap[tokens[tokens.size() - 1]] = new Trie(value);
        return true;
    }

    int get(string path)
    {
        if (path.size() <= 1 && path[0] != '/')
            return false;
        vector<string> tokens = this->split(path);
        struct Trie *t = roots;
        for (int i = 0; i < tokens.size(); i++)
        {
            string token = tokens[i];
            if (!t->umap[token])
                return -1;
            t = t->umap[token];
        }
        return t->val;
    }
};

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem* obj = new FileSystem();
 * bool param_1 = obj->createPath(path,value);
 * int param_2 = obj->get(path);
 */