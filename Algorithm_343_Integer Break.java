// My solution
// 思路：backtracking。数组maxProducts[i]记录数字i按题目要求拆分后所能得到的最大的product
// 对于当前的n，从i = 1开始到n / 2遍历，注意有2种情况
// 第一种情况是n - i被继续拆分，对应于backtracking(n - i)
// 第二种情况是直接用n - i作为另一部分
// 因此maxProducts[n] = Math.max(maxProducts[n], i * Math.max(backtracking(n - i), n - i));

class Solution {
    private int[] maxProducts;
    public int integerBreak(int n) {
        maxProducts = new int[n + 1];
        maxProducts[1] = 1;
        return backtracking(n);
    }
    private int backtracking(int n) {
        if (maxProducts[n] != 0) {
            return maxProducts[n];
        }
        maxProducts[n] = n - 1; // n = 1 + (n - 1), so maxProducts[n] is at least n - 1
        for (int i = 1; i < n / 2; i++) {
            maxProducts[n] = Math.max(maxProducts[n], i * Math.max(backtracking(n - i), n - i));
        }
        return maxProducts[n];
    }
}

// Another Solution
// https://leetcode.com/problems/integer-break/discuss/80721/Why-factor-2-or-3-The-math-behind-this-problem.
// If an optimal product contains a factor f >= 4, then you can replace it with factors 2 and f-2 without losing optimality, as 2*(f-2) = 2f-4 >= f. 
// So you never need a factor greater than or equal to 4, meaning you only need factors 1, 2 and 3 
// (and 1 is of course wasteful and you'd only use it for n=2 and n=3, where it's needed).

class AnotherSolution {
    public int integerBreak(int n) {
        if(n==2) return 1;
        if(n==3) return 2;
        int product = 1;
        while(n>4){
            product*=3;
            n-=3;
        }
        product*=n;
        
        return product;
    }
}