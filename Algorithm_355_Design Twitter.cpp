#include <iostream>
#include <vector>
#include <unordered_map>
#include <unordered_set>
#include <set>
#include <queue>
using namespace std;
class Twitter {
private:
    struct tweet {
        int tweetID;
        int userID;
        tweet(int tID, int uID) : tweetID(tID), userID(uID) {}
    };
    vector<tweet> tweets;
    unordered_map<int, unordered_set<int> > following;
public:
    /** Initialize your data structure here. */
    Twitter() {
        
    }
    
    /** Compose a new tweet. */
    void postTweet(int userId, int tweetId) {
        tweets.push_back(tweet(tweetId, userId));
        following[userId].insert(userId); // a user can be viewed as following himself
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    vector<int> getNewsFeed(int userId) {
        int count = 10;
        vector<int> result;
        unordered_set<int> tmp = following[userId];
        for (int i = tweets.size() - 1; i >= 0; i--)
        {
            if (tmp.find(tweets[i].userID) != tmp.end())
            {
                result.push_back(tweets[i].tweetID);
                count--;
                if (!count) return result;
            }
        }
        return result;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    void follow(int followerId, int followeeId) {
        following[followerId].insert(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    void unfollow(int followerId, int followeeId) {
        if (followerId != followeeId) following[followerId].erase(followeeId);
    }
};

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * vector<int> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */

// better solution
class Twitter {
private:
    int cnt;
    unordered_map<int, set<int>> friends;
    unordered_map<int, vector<pair<int, int>>> tweets;
public:
    /** Initialize your data structure here. */
    Twitter() {
        cnt = 0;
    }
    
    /** Compose a new tweet. */
    void postTweet(int userId, int tweetId) {
        follow(userId, userId);
        tweets[userId].push_back({cnt++, tweetId});
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    vector<int> getNewsFeed(int userId) {
        vector<int> res;
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> q; // minHeap
        for (auto it = friends[userId].begin(); it != friends[userId].end(); it++) { // everyone the user follows
            for (auto a = tweets[*it].begin(); a != tweets[*it].end(); a++) {
                //if (q.size() > 0 && q.top().first > a->first && q.size() > 10) break;
                q.push(*a);
                if (q.size() > 10) q.pop(); // pop the oldest tweet in the current heap
            }
        }
        while (!q.empty()) {
            res.push_back(q.top().second);
            q.pop();
        }
        reverse(res.begin(), res.end());
        return res;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    void follow(int followerId, int followeeId) {
        friends[followerId].insert(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    void unfollow(int followerId, int followeeId) {
        if (followerId != followeeId) {
            friends[followerId].erase(followeeId);
        }
    }
};