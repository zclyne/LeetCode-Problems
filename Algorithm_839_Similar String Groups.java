// 思路：并查集
// 如果两个字符串相似，就在并查集中将他们合并
// 最后返回整个并查集中的连通分量数量，即为答案

class Solution {
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < strs.length - 1; i++) {
            for (int j = i + 1; j < strs.length; j++) {
                if (isSimilar(strs[i], strs[j])) {
                    unionFind.unite(i, j);
                }
            }
        }
        return unionFind.setCount;
    }

    private boolean isSimilar(String a, String b) {
        int countDiff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                countDiff++;
            }
        }
        return countDiff == 0 || countDiff == 2;
    }
}

// 并查集模板
class UnionFind {
    int[] parent;
    int[] size;
    int n;
    // 当前连通分量数目
    int setCount;

    public UnionFind(int n) {
        this.n = n;
        this.setCount = n;
        this.parent = new int[n];
        this.size = new int[n];
        Arrays.fill(size, 1);
        for (int i = 0; i < n; ++i) {
            parent[i] = i;
        }
    }
    
    public int findset(int x) {
        return parent[x] == x ? x : (parent[x] = findset(parent[x]));
    }
    
    public boolean unite(int x, int y) {
        x = findset(x);
        y = findset(y);
        if (x == y) {
            return false;
        }
        if (size[x] < size[y]) {
            int temp = x;
            x = y;
            y = temp;
        }
        parent[y] = x;
        size[x] += size[y];
        --setCount;
        return true;
    }
}
