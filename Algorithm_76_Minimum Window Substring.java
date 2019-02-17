// 思路：用数组map记录t中各个字符出现的次数，变量start和end记录当前s中substring的起始和终止位置
// resLen和resBegin记录最终结果的长度和起始位置，counter记录t中剩余的还未被s的substring中字符所抵消的字符数
// 对s做遍历。当map[s[end]] > 0时，说明在t中该字符仍有剩余，因此可以用s[end]来抵消一个，所以counter--
// 当counter减到0时，表明此时s[start, end)包含了t中所有字符。比较resLen与end - start
// 若后者更小，说明当前substring比先前得到的substring更短，要作为新的结果
// 因此resBegin = start, resLen = end - start。注意end - start不要+1，因为end已经在之前+1
// 为得到更短的substring，把start向后移动。并把从map中扣除的map[s[start]]加回去
// 如果原本map[s[start]] >= 0，表明s[start]被用来抵消t中的字符，因此把start向后移动后，要counter++
// 最终，当end到达s.length()时，遍历结束，得到答案
// 一个处理substring的template：
// int findSubstring(string s){
//     vector<int> map(128,0);
//     int counter; // check whether the substring is valid
//     int begin=0, end=0; //two pointers, one point to tail and one  head
//     int d; //the length of substring

//     for() { /* initialize the hash map here */ }

//     while(end<s.size()){

//         if(map[s[end++]]-- ?){  /* modify counter here */ }

//         while(/* counter condition */){ 
             
//              /* update d here if finding minimum*/

//             //increase begin to make it invalid/valid again
            
//             if(map[s[begin++]]++ ?){ /*modify counter here*/ }
//         }  

//         /* update d here if finding maximum*/
//     }
//     return d;
// }

class Solution {
    public String minWindow(String s, String t) {
        int[] map = new int[128];
        for (int i = 0; i < t.length(); i++) {
            map[(int)t.charAt(i)]++;
        }
        int start = 0, end = 0, resLen = Integer.MAX_VALUE, resBegin = 0, counter = t.length();
        while (end < s.length()) {
            if (map[s.charAt(end)] > 0) { // s[end] can offset a char in t
                counter--;
            }
            map[s.charAt(end++)]--;
            while (counter == 0) { // valid
                if (resLen > end - start) { // has a shorter result, notice that we use end - start rather than end - start + 1
                    resBegin = start; // this is because we've already incremented end before, so here end means the following character of the end of this substring
                    resLen = end - start;
                }
                if (map[s.charAt(start)] >= 0) { // s.charAt(start) is used to offset a char in t
                    counter++;
                }
                map[s.charAt(start++)]++; // remove s.charAt(start) from the substring
            }
        }
        return resLen == Integer.MAX_VALUE ? "" : s.substring(resBegin, resBegin + resLen);
    }
}