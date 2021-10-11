// BFS

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        
        Deque<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        int numStep = 1;
        
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int k = 0; k < len; k++) {
                String curWord = queue.poll();
                if (curWord.equals(endWord)) {
                    return numStep;
                }
                for (int i = 0; i < curWord.length(); i++) {
                    char c = curWord.charAt(i);
                    for (int j = 0; j < 26; j++) {
                        char newC = (char) ('a' + j);
                        String newWord = curWord.substring(0, i) + newC + curWord.substring(i + 1);
                        if (dict.contains(newWord) && !visited.contains(newWord)) {
                            queue.offer(newWord);
                            visited.add(newWord);
                        }
                    }
                }
            }
            numStep++;
        }
        return 0;
    }
}