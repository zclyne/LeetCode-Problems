#include <iostream>
#include <vector>
#include <queue>
#include <unordered_set>
using namespace std;
class Solution {
public:
	struct courseInfo {
		unordered_set<int> pre;
		unordered_set<int> post;
		courseInfo() {}
	};
	bool canFinish(int numCourses, vector<pair<int, int> >& prerequisites) {
		if (prerequisites.size() == 0 || prerequisites.size() == 1) return true;
		vector<courseInfo> courseInfos(numCourses);
		vector<bool> finished(numCourses, false);
		queue<int> courseQueue;
		bool allNeedPre = true;
		courseInfo course;
		for (int i = 0; i < numCourses; i++) courseInfos[i] = course;
		// construct courseInfos
		for (int i = 0; i < prerequisites.size(); i++)
		{
			courseInfos[prerequisites[i].first].post.insert(prerequisites[i].second);
			courseInfos[prerequisites[i].second].pre.insert(prerequisites[i].first);
		}
		// look for courses that doesn't need prerequisites, and push them into courseQueue
		for (int i = 0; i < numCourses; i++)
		{
			if (courseInfos[i].pre.begin() == courseInfos[i].pre.end()) // doesn't need prerequisite
			{
				courseQueue.push(i);
				finished[i] = true;
			}
		}
		if (courseQueue.empty()) return false; // all courses need prerequisites
		while (!courseQueue.empty())
		{
			int curCourse = courseQueue.front();
			courseQueue.pop();
			unordered_set<int>::iterator iter;
			for (iter = courseInfos[curCourse].post.begin(); iter != courseInfos[curCourse].post.end(); iter++)
			{
				int nextCourse = *iter;
				unordered_set<int>::iterator nextIter;
				for (nextIter = courseInfos[nextCourse].pre.begin(); nextIter != courseInfos[nextCourse].pre.end(); nextIter++)
				{
					if (*nextIter == curCourse) // erase current course from all its postcourses' prerequisites set
					{
						courseInfos[nextCourse].pre.erase(nextIter++);
						if (courseInfos[nextCourse].pre.empty()) // all prerequisites finished, add the next course into the queue
						{
							courseQueue.push(nextCourse);
							finished[nextCourse] = true;
						}
						if (nextIter == courseInfos[nextCourse].pre.end()) break;
					}
				}
			}
		}
		for (int i = 0; i < numCourses; i++) if (finished[i] == false) return false;
		return true;
	}
};