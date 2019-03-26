// 思路：当n == 0时，返回1，与x的值无关
// 注意n == Integer.MIN_VALUE时，此时若直接n = -n会产生溢出
// 根据n为奇数还是偶数选择返回值

class Solution {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        } else if (n == Integer.MIN_VALUE) {
            return myPow(x * x, n / 2);
        }
        if (n < 0) {
            n = -n;
            x = 1 / x;
        }
        return n % 2 == 0 ? myPow(x * x, n / 2) : x * myPow(x, n - 1);
    }
}



// other solutions

class Solution2 {
    double myPow(double x, int n) {
        if(n<0) return 1/x * myPow(1/x, -(n+1));
        if(n==0) return 1;
        if(n==2) return x*x;
        if(n%2==0) return myPow( myPow(x, n/2), 2);
        else return x*myPow( myPow(x, n/2), 2);
    }
}

class Solution3 {
    double myPow(double x, int n) { 
        if(n==0) return 1;
        double t = myPow(x,n/2);
        if(n%2!=0) return n<0 ? 1/x*t*t : x*t*t;
        else return t*t;
    }
}

class Solution4 {
    double myPow(double x, int n) { 
        if(n==0) return 1;
        if(n<0) {
            n = -n;
            x = 1/x;
        }
        double ans = 1;
        while(n>0){
            if((n&1)!=0) ans *= x;
            x *= x;
            n >>= 1;
        }
        return ans;
    }
}