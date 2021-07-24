// 方法：直接对各个位上是否为？的情况进行枚举

class Solution {
    public String maximumTime(String time) {
        String hour = "", minute = "";

        // handle hour
        if (time.substring(0, 2).equals("??")) {
            hour = "23";
        } else if (time.charAt(0) == '?') {
            hour = time.charAt(1) <= '3' ? "2" + time.charAt(1) : "1" + time.charAt(1);
        } else if (time.charAt(1) == '?') {
            hour = time.charAt(0) == '2' ? "23" : time.charAt(0) + "9";
        } else {
            hour = time.substring(0, 2);
        }

        // handle minute
        if (time.substring(3, 5).equals("??")) {
            minute = "59";
        } else if (time.charAt(3) == '?') {
            minute = "5" + time.charAt(4);
        } else if (time.charAt(4) == '?') {
            minute = time.charAt(3) + "9";
        } else {
            minute = time.substring(3, 5);
        }

        return hour + ":" + minute;
    }
}