import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

// 方法：用链表的形式来组织用户的tweets
// 定义一个内部类Tweet，记录了用户id、tweet的id、tweet的发送时间postTime，以及指向链表中的下一个tweet的指针
// 用followMap来维护用户之间的关注关系
// 用tweetMap来维护用户id到该用户的最新的tweet的映射关系
// 创建新tweet时，构造一个新的Tweet的实例，其next指针指向原本tweetMap中userId对应的value
// 并且创建完后，令time++
// 这样就保证了每一个tweet的time都唯一且随时间递增，并且tweetMap中，一个用户的tweet链表是按照创建时间从最新到最早排列的
// getNewsFeed方法中，可以用priorityQueue来维护tweet
// 首先将用户userId对应的所有followee的第一个tweet都add到pq中，并把用户userId自己的第一个tweet也放入pq
// 然后不断取pq的根节点（即创建时间最新的tweet），将其放入结果数组result中，并且把当前根节点tweet的next指针指向的tweet也放入pq
// 直到最后result的长度等于10，或pq为空为止

class Twitter {

    private class Tweet implements Comparable<Tweet> {
        public int userId;
        public int tweetId;
        public int postTime;
        public Tweet next;

        public Tweet(int userId, int tweetId, int postTime, Tweet next) {
            this.userId = userId;
            this.tweetId = tweetId;
            this.postTime = postTime;
            this.next = next;
        }

        // order by postTime, decreasing
        @Override
        public int compareTo(Tweet t) {
            return t.postTime - this.postTime;
        }
    }

    private Map<Integer, Set<Integer>> followMap;
    private Map<Integer, Tweet> tweetMap;
    private int time;

    /** Initialize your data structure here. */
    public Twitter() {
        followMap = new HashMap<>();
        tweetMap = new HashMap<>();
        time = 0;
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        Tweet firstTweet = tweetMap.getOrDefault(userId, null);
        Tweet tweet = new Tweet(userId, tweetId, time, firstTweet);
        time++;
        tweetMap.put(userId, tweet);
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        Set<Integer> followees = followMap.getOrDefault(userId, new HashSet<>());
        PriorityQueue<Tweet> pq = new PriorityQueue<>();
        for (int followee : followees) {
            Tweet tweet = tweetMap.getOrDefault(followee, null);
            if (tweet != null) {
                pq.add(tweet);
            }
        }
        // tweets of the user itself should also be considered
        Tweet tweetOfSelf = tweetMap.getOrDefault(userId, null);
        if (tweetOfSelf != null) {
            pq.add(tweetOfSelf);
        }

        List<Integer> result = new ArrayList<>();

        while (result.size() < 10 && !pq.isEmpty()) {
            Tweet latestTweet = pq.poll();
            result.add(latestTweet.tweetId);
            if (latestTweet.next != null) {
                pq.add(latestTweet.next);
            }
        }

        return result;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        Set<Integer> followees = followMap.getOrDefault(followerId, new HashSet<>());
        followees.add(followeeId);
        followMap.put(followerId, followees);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        Set<Integer> followees = followMap.getOrDefault(followerId, new HashSet<>());
        followees.remove(followeeId);
        followMap.put(followerId, followees);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */