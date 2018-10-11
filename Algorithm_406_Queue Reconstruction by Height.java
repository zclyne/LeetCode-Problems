import java.util.ArrayList;

class Solution {
    public int[][] reconstructQueue(int[][] people) {
        if (people.length == 0) return new int[0][0];
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] a, int[] b)
            {
                if (a[0] == b[0]) return a[1] - b[1]; // a has the same h with b, sort by k
                return b[0] - a[0]; // sort by height in descend
            }
        });
        ArrayList<int[]> tmpRes = new ArrayList<>(people.length);
        int[][] result = new int[people.length][2];
        for (int i = 0; i <people.length; i++) tmpRes.add(people[i][1], new int[] {people[i][0], people[i][1]});
        for (int i = 0; i < tmpRes.size(); i++) result[i] = tmpRes.get(i);
        return result;
    }
}