// 思路：比较A[cur]与n的大小，若A[cur] >= n，说明调用next的返回值应该是A[cur + 1]，并从A[cur]中扣除n，表示用掉了n个A[cur + 1]
// 否则，n - A[cur]，并将A[cur]置零，表示用完了所有的A[cur + 1]，cur += 2，重复与n作比较
// 直到出现A[cur] >= n 或cur == A.length，前者表示返回值为A[cur + 1]，后者表示返回值为-1

class RLEIterator {

    private int[] arr;
    private int cur;

    public RLEIterator(int[] A) {
        arr = A;
        cur = 0;
    }
    
    public int next(int n) {
        while (cur < arr.length && arr[cur] < n)
        {
            n -= arr[cur];
            arr[cur] = 0;
            cur += 2;
        }
        if (cur == arr.length)
            return -1;
        else
        {
            arr[cur] -= n;
            return arr[cur + 1];
        }
    }
}

/**
 * Your RLEIterator object will be instantiated and called as such:
 * RLEIterator obj = new RLEIterator(A);
 * int param_1 = obj.next(n);
 */