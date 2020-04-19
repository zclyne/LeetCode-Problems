import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 思路：map totalWordsCount存储words中各个word出现的总次数
// i从0开始遍历到wordLength停止，l记录当前选择的s中的substring的左边界，初始值为i
// r记录当前选择的s中的substring的右边界 - wordLength，初始值为l，递增步长为wordLength
// r的最大值为s.length() - wordLength，从而保证r + wordLength() <= s.length()
// map seenWordsCount记录当前选择的substring中各个word的出现次数
// count记录当前substring中总共的word个数
// 内层循环中，每次从s中取一个新的word，判断该word是否存在于words中
// 若不存在，则直接把l移动到r + wordLength，因为包含当前word的substring一定不满足条件
// 若存在，再判断当前word的出现次数是否已经超过了该word在words中出现的总次数
// 若未超过，则count++，并把该word在seenWordsCount中的value + 1
// 若已经超过，则把左侧边界l向右移动，每次移动wordLength长度，并将最左侧word在
// seenWordsCount中的value - 1，并将count--，直到curWord的次数满足要求
// 注意判断firstWord和curWord是否相同，若相同则无需count--，因为此处是先将word对映的count放入
// seenWordsCount后再开始判断，若firstWord和curWord相同，则可以认为curWord代替了firstWord
// 占据了它在seenWordsCount中的一个位置
// 最后，判断count是否和words的长度相等，若相等，表明words中的word已经全部被使用到
// 把当前的l加入result

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }
        int wordLength = words[0].length();
        if (s.length() < words.length * wordLength) {
            return result;
        }
        Map<String, Integer> totalWordsCount = new HashMap<>();
        for (String word : words) {
            totalWordsCount.put(word, totalWordsCount.getOrDefault(word, 0) + 1);
        }
        for (int i = 0; i < wordLength; i++) {
            // l is the left bound of the current substring selected within s
            // count records the number of words that have been used in words
            int l = i, count = 0;
            Map<String, Integer> seenWordsCount = new HashMap<>();
            for (int r = l; r <= s.length() - wordLength; r += wordLength) {
                String curWord = s.substring(r, r + wordLength);
                if (totalWordsCount.containsKey(curWord)) {
                    int curWordCount = seenWordsCount.getOrDefault(curWord, 0);
                    seenWordsCount.put(curWord, curWordCount + 1);
                    if (seenWordsCount.get(curWord) <= totalWordsCount.get(curWord)) { // curWord is valid
                        count++;
                    } else { // exceed the time that curWord appears in words
                        // move the left pointer to the right until curWord is valid
                        while (seenWordsCount.get(curWord) > totalWordsCount.get(curWord)) {
                            String firstWord = s.substring(l, l + wordLength);
                            seenWordsCount.put(firstWord, seenWordsCount.get(firstWord) - 1);
                            l += wordLength;
                            // must check whether curWord is the same as firstWord because if they are the same
                            // there is no need to decrease count, which represents that curWord has been added
                            // to count
                            if(!curWord.equals(firstWord)) {
                                count--;
                            }
                        }
                    }
                    if (count == words.length) { // already used every word in words
                        result.add(l);
                        // move left to the right by one wordLength
                        count--;
                        String firstWord = s.substring(l, l + wordLength);
                        seenWordsCount.put(firstWord, seenWordsCount.get(firstWord) - 1);
                        l += wordLength;
                    }
                } else { // curWord doesn not exist in words
                    seenWordsCount.clear();
                    l = r + wordLength;
                    count = 0;
                }
            }
        }
        return result;
    }
}