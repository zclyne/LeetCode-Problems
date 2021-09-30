class Solution {
    public boolean isNumber(String s) {
        if (s == null || "".equals(s)) {
            return false;
        }
        s = s.toLowerCase();
        int eIdx = s.indexOf("e");
        if (eIdx == -1) { // s doesn't contain e
            return isDecimal(s);
        } else {
            if (!isDecimal(s.substring(0, eIdx))) {
                return false;
            }
            String secondPart = s.substring(eIdx + 1);
            if ("".equals(secondPart)) {
                return false;
            }
            if (secondPart.charAt(0) == '+' || secondPart.charAt(0) == '-') {
                secondPart = secondPart.substring(1);
            }
            return isInteger(secondPart);
        }
    }

    // check whether s is a decimal
    private boolean isDecimal(String s) {
        if (s == null || "".equals(s)) {
            return false;
        }
        if (s.charAt(0) == '+' || s.charAt(0) == '-') { // exclude the sign
            s = s.substring(1);
        }
        int dotIdx = s.indexOf(".");
        if (dotIdx == -1) { // s doesn't contain dot
            return isInteger(s);
        } else {
            String firstPart = s.substring(0, dotIdx);
            String secondPart = s.substring(dotIdx + 1);
            return "".equals(firstPart) && isInteger(secondPart)
                || isInteger(firstPart) && "".equals(secondPart)
                || isInteger(firstPart) && isInteger(secondPart);
        }
    }

    // check whether s is an integer without sign
    private boolean isInteger(String s) {
        if (s == null || "".equals(s)) {
            return false;
        }
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}