// 思路：HashMap的键是所需要的各个物品的数量，即needs，值为恰好购买这些物品所需的最少的钱数
// 首先判断special offers中是否存在这样的offer，其价格高于单独购买该offer中的每一件商品时的总价，若存在，则将其删除
// 在做DFS的过程中，首先判断当前needs是否已经存在在map中，若存在，则map.get(needs)一定是购买needs所有商品所要花的最少的钱，直接返回即可
// 若不存在，则对所有offer遍历，找可行的offer(即needs中的各个物品数量减去offer中的各个物品数量后大于等于0)
// 若某offer可行，选取该offer，递归调用DFS得到选取该offer时购买needs中所有商品要花的最少的钱，这样遍历找到所有offer中最优的（即花钱最少）
// 若所有offer中都不存在可行的offer，表明只能单独买needs中的各个商品
// 最后，将花的钱存入map，并作为返回值即可

import java.util.ArrayList;
import java.util.HashMap;

class Solution {
    private List<List<Integer>> offers;
    private HashMap<List<Integer>, Integer> map;
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        map = new HashMap<>();
        offers = new ArrayList<>();
        // check if there exists any special offer that is more expensive buying each item in it respectively
        for (int i = 0; i < special.size(); i++) {
            List<Integer> currentOffer = special.get(i);
            int respectiveSum = 0;
            for (int j = 0; j < currentOffer.size() - 1; j++)
                respectiveSum += price.get(j) * currentOffer.get(j);
            if (respectiveSum > currentOffer.get(currentOffer.size() - 1)) // the special offer is cheaper
                offers.add(currentOffer);
        }
        return DFS(price, offers, needs);
    }
    public int DFS(List<Integer> price, List<List<Integer>> offers, List<Integer> needs) {
        if (!map.containsKey(needs)) {
            int minMoney = Integer.MAX_VALUE;
            for (int i = 0; i < offers.size(); i++) {
                List<Integer> currentOffer = offers.get(i);
                boolean isAvailableOffer = true;
                for (int j = 0; j < currentOffer.size() - 1; j++) { // check if this offer is available
                    if (needs.get(j) < currentOffer.get(j)) { // currentOffer contains more items than we need
                        isAvailableOffer = false;
                        break;
                    }
                }
                if (isAvailableOffer) {
                    List<Integer> tempNeeds = new ArrayList<>();
                    for (int j = 0; j < currentOffer.size() - 1; j++)
                        tempNeeds.add(needs.get(j) - currentOffer.get(j));
                    int moneyWithThisOffer = currentOffer.get(currentOffer.size() - 1) + DFS(price, offers, tempNeeds);
                    if (moneyWithThisOffer < minMoney)
                        minMoney = moneyWithThisOffer;
                }
            }
            if (minMoney == Integer.MAX_VALUE) { // no available offer, buy each item respectively
                minMoney = 0;
                for (int j = 0; j < needs.size(); j++)
                    minMoney += needs.get(j) * price.get(j);
            }
            map.put(needs, minMoney);
        }
        return map.get(needs);
    }
}