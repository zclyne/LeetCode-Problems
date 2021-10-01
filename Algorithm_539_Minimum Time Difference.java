// 方法：直接转换到int表示的minute，存储在数组里，然后遍历一遍数组

class Solution {
    public int findMinDifference(List<String> timePoints) {
        int[] minutes = new int[1440];
        int minMinute = 1440, maxMinute = -1;
        for (String timeStr : timePoints) {
            int time = timeStrToInt(timeStr);
            if (minutes[time] > 0) { // this time already exists
                return 0;
            }
            minutes[time]++;
            minMinute = Math.min(minMinute, time);
            maxMinute = Math.max(maxMinute, time);
        }
        int result = minMinute + 1440 - maxMinute;
        int lastMinute = minMinute;
        for (int i = minMinute + 1; i < 1440; i++) {
            if (minutes[i] > 0) {
                result = Math.min(result, i - lastMinute);
                lastMinute = i;
            }
        }
        return result;
    }

    private int timeStrToInt(String timeStr) {
        return Integer.parseInt(timeStr.substring(0, 2)) * 60 + Integer.parseInt(timeStr.substring(3));
    }
}