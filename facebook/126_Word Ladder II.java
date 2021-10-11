// BFS

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        List<List<String>> result = new ArrayList<>();

        if (!dict.contains(endWord)) {
            return result;
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.offer(beginWord);
        int shortestPathLength = 0;

        while (!queue.isEmpty()) {
            String str = queue.poll();
            String[] words = str.split(",");
            String curWord = words[words.length - 1];
            visited.add(curWord);

            // change each letter and see whether the new word exists in dict
            for (int i = 0; i < curWord.length(); i++) {
                for (int j = 0; j < 26; j++) {
                    char c = (char) ('a' + j);
                    String newWord = curWord.substring(0, i) + c + curWord.substring(i + 1);
                    if (newWord.equals(endWord)) { // found an valid path to the endWord
                        if (shortestPathLength == 0 || words.length + 1 == shortestPathLength) { // path not found before, this is the shortest path
                            List<String> path = new ArrayList<>();
                            path.addAll(Arrays.asList(words));
                            path.add(endWord);
                            result.add(path);
                            shortestPathLength = path.size();
                        } else { // not the shortest path, no need to continue the BFS
                            return result;
                        }
                    } else if (dict.contains(newWord) && !visited.contains(newWord)) { // newWord exists in dict and is not visited before
                        String newStr = str + ',' + newWord; // append the newWord to the string so that the path is automatically saved
                        queue.offer(newStr);
                    }
                }
            }
        }

        return result;
    }
}