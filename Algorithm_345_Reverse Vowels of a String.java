// 方法：双指针，分别从左到右和从右到左扫描s，查找元音字母，然后交换，直到左右指针相遇

class Solution {
    public String reverseVowels(String s) {
        int left = 0, right = s.length() - 1;
        char[] sc = s.toCharArray();
        while (left < right) {
            // also need to check the relationship between left and right here
            while (left < right && !isVowel(sc[left])) {
                left++;
            }
            // also need to check the relationship between left and right here
            while (left < right && !isVowel(sc[right])) {
                right--;
            }

            // exchange
            char temp = sc[left];
            sc[left] = sc[right];
            sc[right] = temp;
            left++;
            right--;
        }

        return new String(sc);
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
            || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }
}