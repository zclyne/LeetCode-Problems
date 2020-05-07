// 思路：数组leads中的leads[i]记录在times[i]时刻票数最多的person
// 在初始化阶段，遍历一遍persons并填充数组leads
// q()中，先用二分查找的方法找到idx，使得times[idx] <= t
// leads[idx]就是t时刻票数最多的person

class TopVotedCandidate {

    private int[] leads;
    private int[] times;

    public TopVotedCandidate(int[] persons, int[] times) {
        int[] voteCounts = new int[persons.length];
        leads = new int[persons.length];
        this.times = times;
        int curLead = -1, curMaxVote = 0;
        for (int i = 0; i < persons.length; i++) {
            int person = persons[i];
            // notice >= because if there is a tie, the most recent vote wins
            if (++voteCounts[person] >= curMaxVote) {
                curLead = person;
                curMaxVote = voteCounts[person];
            }
            leads[i] = curLead;
        }
    }
    
    public int q(int t) {
        int idx = binarySearch(t);
        return leads[idx];
    }

    // return the biggest idx in times that times[idx] <= t
    private int binarySearch(int t) {
        int left = 0, right = times.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (times[mid] > t) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */