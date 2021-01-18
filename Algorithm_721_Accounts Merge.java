import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

// 解法1：并查集
// 先用map存储email到账户index的映射，在构建过程中使用并查集合并具有相同email的账户
// 然后反向构建账户到所有属于该账户的email的映射

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> result = new ArrayList<>();
        Map<String, Integer> emailToIndex = new HashMap<>();
        UnionFind unionFind = new UnionFind(accounts.size());

        // construct the mapping from email to account index
        // and merge accounts with the same email
        for (int i = 0; i < accounts.size(); i++) {
            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);
                if (!emailToIndex.containsKey(email)) { // email shows up for the first time
                    emailToIndex.put(email, i);
                } else { // email exists for another account, merge them
                    unionFind.union(i, emailToIndex.get(email));
                }
            }
        }

        // construct the mapping from each account index to all the emails the account owns
        Map<Integer, List<String>> indexToEmails = new HashMap<>();
        for (Map.Entry<String, Integer> entry : emailToIndex.entrySet()) {
            String email = entry.getKey();
            Integer index = entry.getValue();
            Integer rootIndex = unionFind.find(index);
            List<String> emails = indexToEmails.getOrDefault(rootIndex, new ArrayList<>());
            emails.add(email);
            indexToEmails.put(rootIndex, emails);
        }

        // construct the result list
        for (Map.Entry<Integer, List<String>> entry : indexToEmails.entrySet()) {
            Integer index = entry.getKey();
            String accountName = accounts.get(index).get(0);
            List<String> emails = entry.getValue();
            Collections.sort(emails);
            emails.add(0, accountName); // insert the account's name to the head of the list
            result.add(emails);
        }

        return result;
    }
}

class UnionFind {
    private int[] parent;
    private int[] rank; // records the depth of each root in order to speed up operations

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int i, int j) {
        int x = find(i), y = find(j);
        if (rank[x] <= rank[y]) { // make y the root of x
            parent[x] = y;
        } else { // make x the root of y
            parent[y] = x;
        }
        if (rank[x] == rank[y] && x != y) { // the depth of the two trees are the same, increment the depth of the root
            rank[y]++;
        }
    }
}

// 解法2：DFS
// 如果两个email同时出现在一个account中，就在它们之间画一条边
// 对每一个account的所有email，从它的第一个email到其他所有email画一条边，并记录一个从email到账号name的map
// 使用DFS来寻找连接起来的部分

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emailToName = new HashMap();
        Map<String, ArrayList<String>> graph = new HashMap();

        // construct the graph and mapping from email to account name
        for (List<String> account: accounts) {
            String name = account.get(0);
            String firstEmail = account.get(1);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                graph.computeIfAbsent(email, x -> new ArrayList<String>()).add(firstEmail); // edge from current email to the first email
                graph.computeIfAbsent(firstEmail, x -> new ArrayList<String>()).add(email); // edge from first email to the current email
                emailToName.put(email, name); // mapping from email to account name
            }
        }

        Set<String> seen = new HashSet();
        List<List<String>> result = new ArrayList();
        for (String email: graph.keySet()) {
            if (!seen.contains(email)) {
                seen.add(email);
                Stack<String> stack = new Stack();
                stack.push(email); // either stack or queue should be OK
                List<String> component = new ArrayList();
                while (!stack.empty()) {
                    String node = stack.pop();
                    component.add(node);
                    for (String nei: graph.get(node)) { // for each connected emails, add to component if it isn't seen
                        if (!seen.contains(nei)) {
                            seen.add(nei);
                            stack.push(nei);
                        }
                    }
                }
                Collections.sort(component);
                component.add(0, emailToName.get(email));
                result.add(component);
            }
        }
        return result;
    }
}