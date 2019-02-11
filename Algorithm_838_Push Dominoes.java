// 思路：

class Solution {
    public String pushDominoes(String dominoes) {
        char lastState = '.';
        int lastIdx = 0;
        StringBuilder builder = new StringBuilder(dominoes);
        for (int i = 0; i < dominoes.length(); i++) {
            if (lastState == '.') {
                if (dominoes.charAt(i) == 'L') {
                    for (int j = lastIdx; j < i; j++) {
                        builder.setCharAt(j, 'L');
                    }
                    lastIdx = i + 1;
                } else if (dominoes.charAt(i) == 'R') {
                    lastState = 'R';
                    lastIdx = i;
                }
            } else if (lastState == 'R') {
                if (dominoes.charAt(i) == '.') {
                    builder.setCharAt(i, 'R');
                } else if (dominoes.charAt(i) == 'R') {
                    lastIdx = i;
                } else { // dominoes.charAt(i) == 'L'
                    int middleIdx = (i + lastIdx) / 2;
                    for (int j = middleIdx + 1; j < i; j++) {
                        builder.setCharAt(j, 'L');
                    }
                    if ((i - lastIdx) % 2 == 0) {
                        builder.setCharAt(middleIdx, '.');
                    }
                    lastState = '.';
                    lastIdx = i + 1;
                }
            }
        }
        return builder.toString();
    }
}

// another solution
// class Solution {
//     public String pushDominoes(String d) {
//         d = 'L' + d + 'R';
//         StringBuilder res = new StringBuilder();
//         for (int i = 0, j = 1; j < d.length(); ++j) {
//             if (d.charAt(j) == '.') continue;
//             int middle = j - i - 1;
//             if (i > 0) res.append(d.charAt(i));
//             if (d.charAt(i) == d.charAt(j))
//                 for (int k = 0; k < middle; k++) res.append(d.charAt(i));
//             else if (d.charAt(i) == 'L' && d.charAt(j) == 'R')
//                 for (int k = 0; k < middle; k++) res.append('.');
//             else {
//                 for (int k = 0; k < middle / 2; k++) res.append('R');
//                 if (middle % 2 == 1) res.append('.');
//                 for (int k = 0; k < middle / 2; k++) res.append('L');
//             }
//             i = j;
//         }
//         return res.toString();
//     }
// }