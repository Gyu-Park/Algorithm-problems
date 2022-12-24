#include <iostream>
#include <queue>
using namespace std;

class HitCounter
{
public:
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

private:
    queue<int> hits;
};