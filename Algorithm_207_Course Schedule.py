class Solution:
    def canFinish(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
        courses = [[] for _ in range(numCourses)]
        numOfPrerequisitesRemain = [0 for _ in range(numCourses)]
        for prerequisite in prerequisites:
            pre, post = prerequisite[1], prerequisite[0]
            courses[pre].append(post)
            numOfPrerequisitesRemain[post] += 1
        queue = []
        numOfCoursesFinished = 0
        # look for available courses
        for i in range(len(numOfPrerequisitesRemain)):
            if numOfPrerequisitesRemain[i] == 0: # course i doesn't have any prerequisites
                queue.append(i)
        while queue:
            cur = queue.pop(0)
            numOfCoursesFinished += 1
            postCourses = courses[cur]
            for postCourse in postCourses:
                numOfPrerequisitesRemain[postCourse] -= 1
                if numOfPrerequisitesRemain[postCourse] == 0: # can take this course now
                    queue.append(postCourse)
        return numOfCoursesFinished == numCourses