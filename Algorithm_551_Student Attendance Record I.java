class Solution {
    public boolean checkRecord(String s) {
        int absentCount = 0;
        int consecutiveLaterCount = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'L') {
                consecutiveLaterCount++;
            } else {
                consecutiveLaterCount = 0;
                if (c == 'A') {
                    absentCount++;
                }
            }
            if (absentCount >= 2 || consecutiveLaterCount >= 3) {
                return false;
            }
        }

        return true;
    }
}