// 思路：贪心法，只要遇到能够种花的位置，就种花上去

class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (isValid(flowerbed, i)) {
                flowerbed[i] = 1;
                count++;
            }
        }
        return count >= n;
    }

    private boolean isValid(int[] flowerbed, int i) {
        boolean leftValid = i == 0 || flowerbed[i - 1] == 0;
        boolean rightValid = i == flowerbed.length - 1 || flowerbed[i + 1] == 0;
        return flowerbed[i] == 0 && leftValid && rightValid;
    }
}