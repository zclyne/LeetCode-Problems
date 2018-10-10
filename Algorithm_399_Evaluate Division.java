import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class Solution {
    private HashMap<String, ArrayList<String> > pairs;
    private HashMap<String, ArrayList<Double> > valuesPair;
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        // construct graph
        pairs = new HashMap<>();
        valuesPair = new HashMap<>();
        for (int i = 0; i < equations.length; i++)
        {
            String[] equation = equations[i];
            if (!pairs.containsKey(equation[0]))
            {
                pairs.put(equation[0], new ArrayList<String> ());
                valuesPair.put(equation[0], new ArrayList<Double>());
            }
            if (!pairs.containsKey(equation[1]))
            {
                pairs.put(equation[1], new ArrayList<String> ());
                valuesPair.put(equation[1], new ArrayList<Double>());
            }
            pairs.get(equation[0]).add(equation[1]);
            pairs.get(equation[1]).add(equation[0]);
            valuesPair.get(equation[0]).add(values[i]);
            valuesPair.get(equation[1]).add(1 / values[i]);
        }

        // dfs
        double[] res = new double[queries.length];
        for (int i = 0; i < queries.length; i++)
        {
            HashSet<String> visited = new HashSet<>();
            res[i] = dfs(queries[i][0], queries[i][1], visited);
        }
        return res;
    }
    public double dfs(String cur, String end, HashSet<String> visited)
    {
        if (!pairs.containsKey(cur) || !pairs.containsKey(end)) return -1.0; // invalid start node or invalid end node
        if (visited.contains(cur)) return -1.0; // has already visited this node
        if (cur.equals(end)) return 1.0; // already get the end
        visited.add(cur);
        ArrayList<String> nextNodes = pairs.get(cur);
        ArrayList<Double> weights = valuesPair.get(cur);
        for (int i = 0; i < nextNodes.size(); i++)
        {
            String nextNode = nextNodes.get(i);
            double tmpEdgeWeight = weights.get(i);
            double tmpRes = dfs(nextNode, end, visited);
            if (tmpRes != -1.0) return tmpEdgeWeight * tmpRes;
        }
        visited.remove(cur);
        return -1.0;
    }
}