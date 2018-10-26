// 思路：直接对输入的S做遍历，将当前位上的字符与上一个字符作比较，看是否相同。count记录以end结尾的连续的相同字符的长度
// 若相同，count++，start不变
// 若不相同，检查上一次留下的count是否>=3，若是，说明以end-1结尾的字符串满足条件，将[start, end-1]加入答案中
// 最后将count置为1，并将start置为当前位
// 遍历结束后，还要检查一次count是否>=3，解决形如”aaa“的情况，即以最后一个字符结尾的字符串也满足条件

class Solution {
    public List<List<Integer>> largeGroupPositions(String S) {
        if (S.length() <= 2) return new ArrayList<List<Integer> >();
        int count = 1, start = 0, end = 0;
        List<List<Integer>> res = new ArrayList<>();
        for (end = 1; end < S.length(); end++)
        {
            if (S.charAt(end) == S.charAt(end - 1)) count++;
            else
            {
                if (count >= 3)
                {
                    ArrayList<Integer> tmp = new ArrayList<>();
                    tmp.add(start);
                    tmp.add(end - 1);
                    res.add(tmp);
                }
                count = 1;
                start = end;
            }
        }
        // deal with the last group
        if (count >= 3)
        {
            ArrayList<Integer> tmp = new ArrayList<>();
            tmp.add(start);
            tmp.add(end - 1);
            res.add(tmp);
        }
        return res;
    }
}