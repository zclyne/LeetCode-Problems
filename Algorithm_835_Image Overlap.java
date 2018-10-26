// 思路：直接对数组A遍历，若A[i][j]为1，则遍历一遍数组B，找到B中所有为1的位置，并将这两个点的坐标的差值记录在数组record中
// 最后遍历一遍record，找到record中所有位置上的最大值，即为答案
// 原理：将一张图片做偏移时，所有对应点的坐标的相对位置都恒定

// fast solution
class Solution {
    public int largestOverlap(int[][] A, int[][] B) {
        int n = A.length;
        int[][] record = new int[2 * n][2 * n];
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(A[i][j] == 1) {
                    for(int k = 0; k < n; k++) {
                        for(int q = 0; q < n; q++) {
                            if(B[k][q] == 1){
                                record[k-i+n][q-j+n] += 1;
                            }
                        }
                    }
                }
            }
        }
        
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < record.length; i++) {
            for(int j = 0; j < record[0].length; j++) {
                max = Math.max(max, record[i][j]);
            }
        }
        return max;
    }
}

// 注意：不能使用数组作为Map的键，因为数组的equals()是基于引用比较的，即使2个数组的所有元素都相同，也不会认为两数组相等
// very slow solution
class Solution {
    public int largestOverlap(int[][] A, int[][] B) {
        ArrayList<int[]> onesInA = new ArrayList<>();
        ArrayList<int[]> onesInB = new ArrayList<>();
        int n = A.length, maximum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 1) onesInA.add(new int[] {i, j});
                if (B[i][j] == 1) onesInB.add(new int[] {i, j});
            }
        }
        HashMap<String, Integer> results = new HashMap<>();
        for (int[] a : onesInA) {
            for (int[] b : onesInB) {
                int delta_x = a[0] - b[0], delta_y = a[1] - b[1];
                String coordinate = delta_x + ", " + delta_y;
                Integer tmpCount = results.get(coordinate);
                if (tmpCount == null) results.put(coordinate, 1);
                else results.put(coordinate, tmpCount + 1);
                maximum = Math.max(maximum, results.get(coordinate));
                
            }
        }
        return maximum;
    }
}