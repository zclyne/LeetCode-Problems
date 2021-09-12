class Solution {
    public String reversePrefix(String word, char ch) {
        boolean exist = false;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            stringBuilder.append(word.charAt(i));
            if (word.charAt(i) == ch) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            return word;
        }
        return stringBuilder.reverse().toString() + word.substring(stringBuilder.length());
    }
}