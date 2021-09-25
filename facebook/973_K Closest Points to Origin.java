import java.util.Comparator;
import java.util.PriorityQueue;

// quick select

class Solution {
    public int[][] kClosest(int[][] points, int K) {
        int len =  points.length, l = 0, r = len - 1;
        while (l <= r) {
            int mid = helper(points, l, r);
            if (mid == K - 1) break;
            if (mid < K) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return Arrays.copyOfRange(points, 0, K);
    }

    private int helper(int[][] A, int l, int r) {
        int start = l;
        int[] pivot = A[l];
        while (l < r) {
            while (l < r && compare(A[r], pivot) >= 0) r--;
            while (l < r && compare(A[l], pivot) <= 0) l++;
            // swap
            int[] temp = A[l];
            A[l] = A[r];
            A[r] = temp;
        }
        // swap pivot and A[start]
        int[] temp = A[l];
        A[l] = pivot;
        A[start] = temp;
        return l;
    }

    private int compare(int[] p1, int[] p2) {
        return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
    }
}

// max-heap

class Solution {

    public int[][] kClosest(int[][] points, int k) {
        // max-heap
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            int dist1 = a[0] * a[0] + a[1] * a[1];
            int dist2 = b[0] * b[0] + b[1] * b[1];
            return dist2 - dist1;
        });

        for (int i = 0; i < k; i++) {
            pq.offer(points[i]);
        }

        for (int i = k; i < points.length; i++) {
            pq.offer(points[i]);
            pq.poll();
        }

        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            result[i] = pq.poll();
        }

        return result;
    }
}

// quick-select

class Solution {
    public int[][] kClosest(int[][] points, int k) {
        public int[][] kClosest(int[][] points, int K) {
            int len =  points.length, l = 0, r = len - 1;
            while (l <= r) {
                int mid = helper(points, l, r);
                if (mid == K) break;
                if (mid < K) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            return Arrays.copyOfRange(points, 0, K);
        }
        
        private int helper(int[][] A, int l, int r) {
            int[] pivot = A[l];
            while (l < r) {
                while (l < r && compare(A[r], pivot) >= 0) r--;
                A[l] = A[r];
                while (l < r && compare(A[l], pivot) <= 0) l++;
                A[r] = A[l];
            }
            A[l] = pivot;
            return l;
        }
        
        private int compare(int[] p1, int[] p2) {
            return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
        }
    }
}