class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] charsCount = new int[26];
        for (char c : magazine.toCharArray()) {
            charsCount[c - 'a']++;
        }

        for (char c : ransomNote.toCharArray()) {
            if (charsCount[c - 'a'] == 0) {
                return false;
            }
            charsCount[c - 'a']--;
        }

        return true;
    }
}