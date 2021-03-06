import java.util.ArrayList;
import java.util.List;

// 思路：先试图把word加入同一行，直到即将超出maxWidth限制为止
// 变量numOfLetters记录这一行中所有单词的字母数总和
// 此时这行中剩余的空格数量就等于maxWidth - numOfLetters
// 对这一行中的单词数量 - 1取模，从而计算出每个空格应放在哪个单词后面
// 如果这一行只有1个单词，那么所有空格都应该放在这个唯一的单词之后，因此对1取模
// 处理完一行后，把curLine中所有的单词连接起来，放入result中
// 对于最后一行要特殊处理，因为最后一行没有进入for循环中的if判断，因此最后一行对应的
// curLine中的每个单词都不包含后缀的空格，因此需要用一个空格来join，而不能用空字符串
// join完成后，再在这个字符串的最后用空格填充到maxWidth宽度，并放入result中

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        List<String> curLine = new ArrayList<>();
        int numOfLetters = 0;
        for (String word : words) {
            // numOfLetters equals the total number of letters of all words in this line
            // curLine.size() equals the number of spaces between words in this line 
            if (word.length() + numOfLetters + curLine.size() > maxWidth) { // word cannot be added to curLine
                for (int i = 0; i < maxWidth - numOfLetters; i++) { // the number of remained spaces is maxWidth - numOfLetters
                    int mod = curLine.size() == 1 ? 1 : curLine.size() - 1;
                    String tmpWord = curLine.get(i % mod); // evenly append space to each word in this line by using modulus
                    tmpWord += ' ';
                    curLine.set(i % mod, tmpWord);
                }
                result.add(String.join("", curLine));
                curLine.clear();
                numOfLetters = 0;
            }
            curLine.add(word);
            numOfLetters += word.length();
        }
        // the last line, append spaces behind the last word
        // we didn't add any spaces to words in the last line, so we should join the words
        // with a space, rather than empty string
        result.add(padRight(String.join(" ", curLine), maxWidth));
        return result;
    }

    // pad the given string with spaces on the right side to n letters in total
    private String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }
}



// 二刷解法

class SecondSolution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int curStart = 0, curWordCount = 0, curWordsTotalLength = 0;
        for (int i = 0; i < words.length; i++) {
            String curWord = words[i];
            int curSlotCount = curWordCount - 1;
            if (curWordsTotalLength + curSlotCount + curWord.length() + 1 > maxWidth) { // exceed the maxWidth limit
                if (curWordCount == 1) { // only one word, still has a slot
                    curSlotCount = 1;
                }
                int remainingLength = maxWidth - curWordsTotalLength;
                int spacePerSlot = remainingLength / curSlotCount;
                int remainder = remainingLength % curSlotCount;
                result.add(this.buildLine(words, curStart, i - 1, spacePerSlot, remainder));
                curStart = i; // start a new line
                i--; // re-consider curWord for the next line
                curWordCount = 0;
                curWordsTotalLength = 0;
            } else { // can append curWord to this line
                curWordCount++;
                curWordsTotalLength += curWord.length();
            }
        }
        // the last line
        int remainingLength = maxWidth - curWordsTotalLength;
        int spacePerSlot = remainingLength == 0 ? 0 : 1;
        String lastLine = this.buildLine(words, curStart, words.length - 1, spacePerSlot, 0);
        while (lastLine.length() < maxWidth) {
            lastLine = lastLine + " ";
        }
        result.add(lastLine);
        return result;
    }

    private String buildLine(String[] words, int start, int end, int spacePerSlot, int remainder) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = start; i <= end; i++) {
            stringBuilder.append(words[i]);
            if (i < end || start == end) {
                for (int j = 0; j < spacePerSlot; j++) {
                    stringBuilder.append(' ');
                }
                if (remainder > 0) { // the empty slots on the left will be assigned more spaces than the slots on the right
                    stringBuilder.append(' ');
                    remainder--;
                }
            }
        }
        return stringBuilder.toString();
    }
}