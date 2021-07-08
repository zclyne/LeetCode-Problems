import java.util.ArrayList;
import java.util.List;

// 方法：直接从左到右遍历
// 将num拆分成billion, million, thousand, < 1000这4个部分
// 每部分都是一个<1000的正整数，用函数helper直接构建出这部分的英语单词表达
// 然后分别添加上Billion, Million, Thousand这些量词来连接

class Solution {

    String[] onesDigit = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    String[] tensDigit = new String[]{"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    String[] elevenToNineTeen = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        List<String> result = new ArrayList<>();

        // billions
        num = getWords(num, 1000000000, "Billion", result);

        // millions
        num = getWords(num, 1000000, "Million", result);

        // thousands
        num = getWords(num, 1000, "Thousand", result);

        // less than 1000
        getWords(num, 1, "", result);

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < result.size(); i++) {
            stringBuilder.append(result.get(i));
            if (i != result.size() - 1) {
                stringBuilder.append(' ');
            }
        }
        return stringBuilder.toString();
    }

    private int getWords(int num, int base, String quantifier, List<String> result) {
        int tmp = num;
        if (base > 1) {
            tmp /= base;
            num %= base;
        }
        List<String> words = helper(tmp);
        if (!words.isEmpty()) {
            result.addAll(words);
            if (!"".equals(quantifier)) {
                result.add(quantifier);
            }
        }
        return num;
    }

    // build the English words for n < 1000
    private List<String> helper(int n) {
        List<String> result = new ArrayList<>();
        if (n == 0) {
            return result;
        }
        
        int hundreds = n / 100;
        if (hundreds > 0) {
            result.add(onesDigit[hundreds]);
            result.add("Hundred");
        }
        n %= 100;

        int tens = n / 10;
        int ones = n % 10;
        if (tens == 1) {
            result.add(elevenToNineTeen[ones]);
        } else {
            if (!"".equals(tensDigit[tens])) {
                result.add(tensDigit[tens]);
            }
            if (!"".equals(onesDigit[ones])) {
                result.add(onesDigit[ones]);
            }
        }

        return result;
    }
}