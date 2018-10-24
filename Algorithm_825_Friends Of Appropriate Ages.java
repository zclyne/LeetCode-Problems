// 思路：对原数组遍历一遍，将年龄为i的人的个数记录在numOfPeople[i]中
// 再对numOfPeople遍历一遍，将年龄从1到i的所有人的总数记录在sumOfPeople[i]中
// 最后，按年龄从15到120做遍历，注意这里必须从15开始，因为要保证age > age / 2 + 7
// sumOfPeople[age] - sumOfPeople[lowerBound]计算出所有年龄满足条件的人的总数
// 该总数乘以年龄为age的人数，这是因为每个年龄为age的人都会做出这么多次request
// 最后再减掉年龄为age的人数，这是因为每个人不能friend request自己
// 再将得到的值加到result中，循环结束后返回result


class Solution {
    public int numFriendRequests(int[] ages) {
        int[] numOfPeople = new int[121], sumOfPeople = new int[121];
        int res = 0;
        for (int age : ages) numOfPeople[age]++;
        for (int i = 1; i <= 120; i++) sumOfPeople[i] = sumOfPeople[i - 1] + numOfPeople[i];
        for (int age = 15; age <= 120; age++)
        {
            if (numOfPeople[age] == 0) continue;
            int lowerBound = (int) (0.5 * age + 7);
            int tmpCount = (sumOfPeople[age] - sumOfPeople[lowerBound]) * numOfPeople[age] - numOfPeople[age];
            res += tmpCount;
        }
        return res;
    }
}