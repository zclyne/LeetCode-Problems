// 思路：先正序遍历，确保若右边的child的rating比他相邻的左边的child的rating要高，则右边的child拥有更多candy
// 再逆序遍历，确保若左边的child的rating比他相邻的右边的child的rating要高，则左边的child拥有更多的candy
// 左后遍历一遍candies，把每个child拥有的candy数求和，就是结果

class Solution {
    public int candy(int[] ratings) {
        int result = 0;
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);
        // every child with a higher rating than the child on his or her left
        // should get more candies
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        // every child with a higher rating than the child on his or her right
        // should get more candies
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }
        for (int candy : candies) {
            result += candy;
        }
        return result;
    }
}