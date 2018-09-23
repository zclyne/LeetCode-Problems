#include <iostream>
#include <vector>
#include <queue>
#include <unordered_set>
using namespace std;
class Solution {
public:
    vector<int> findOrder(int numCourses, vector<pair<int, int>>& prerequisites) {
        vector<unordered_set<int> > graph(numCourses);
        unordered_set<int> postCourses;
        vector<int> inDegree(numCourses, 0);
        vector<int> result;
        queue<int> courseQueue;
        int numFinished = 0;
        for (int i = 0; i < numCourses; i++) graph[i] = postCourses;
        for (int i = 0; i < prerequisites.size(); i++)
        {
            graph[prerequisites[i].second].insert(prerequisites[i].first);
            inDegree[prerequisites[i].first]++;
        }
        for (int i = 0; i < numCourses; i++) if (inDegree[i] == 0) courseQueue.push(i);
        while (!courseQueue.empty())
        {
            int curCourse = courseQueue.front();
            courseQueue.pop();
            numFinished++;
            result.push_back(curCourse);
            for (unordered_set<int>::iterator iter = graph[curCourse].begin(); iter != graph[curCourse].end(); iter++)
            {
                inDegree[*iter]--;
                if (inDegree[*iter] == 0) courseQueue.push(*iter); // all prerequisites are finished, add it to the queue
            }
        }
        if (numFinished != numCourses) result.clear();
        return result;
    }
};