// 方法：用数组digitCount记录下secret中0-9每个数字出现的次数
// 然后遍历guess两遍。第一遍里，找到所有的bull，并相应地从digitCount中减去各数字出现的次数
// 第二遍找到所有的cow
// 注意这两遍遍历不能合并，不然有可能出现cow把digitCount[guessedDigit]减到0，导致bull结果错误的情况

class Solution {
    public String getHint(String secret, String guess) {
        int n = secret.length();
        int[] digitCount = new int[10];
        for (int i = 0; i < n; i++) {
            int digit = secret.charAt(i) - '0';
            digitCount[digit]++;
        }

        int bull = 0, cow = 0;
        for (int i = 0; i < n; i++) {
            int guessedDigit = guess.charAt(i) - '0';
            int correctDigit = secret.charAt(i) - '0';
            if (guessedDigit == correctDigit) {
                bull++;
                digitCount[guessedDigit]--;
            }
        }

        for (int i = 0; i < n; i++) {
            int guessedDigit = guess.charAt(i) - '0';
            int correctDigit = secret.charAt(i) - '0';
            if (guessedDigit != correctDigit && digitCount[guessedDigit] > 0) {
                cow++;
                digitCount[guessedDigit]--;
            }
        }

        return bull + "A" + cow + "B";
    }
}