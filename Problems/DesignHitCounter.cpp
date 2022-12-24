#include <iostream>
#include <queue>
using namespace std;

class HitCounter
{
public:
    queue<int> hits;
    HitCounter()
    {
    }

    void hit(int timestamp)
    {
        this->hits.push(timestamp);
    }

    int getHits(int timestamp)
    {
        while (!this->hits.empty() && timestamp - this->hits.front() >= 300)
        {
            this->hits.pop();
        }
        return this->hits.size();
    }
};