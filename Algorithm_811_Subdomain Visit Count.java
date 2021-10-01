import java.util.*;

// 方法：用map存储domain到访问量的映射
// 遍历cpdomains，对于其中的每一个网址，不断将其拆分成更小的domain
// 最后再从map重建result

class Solution {
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : cpdomains) {
            String[] domainInfo = s.split(" ");
            int count = Integer.parseInt(domainInfo[0]);
            String domain = domainInfo[1];
            while (true) {
                map.put(domain, map.getOrDefault(domain, 0) + count);
                int dotIdx = domain.indexOf(".");
                if (dotIdx == -1) {
                    break;
                }
                domain = domain.substring(dotIdx + 1);
            }
        }
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            result.add(entry.getValue() + " " + entry.getKey());
        }
        return result;
    }
}