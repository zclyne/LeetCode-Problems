import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Map;

// 方法：用两个set foods和tables分别存储food名称和table号，在遍历orders时，利用set的特性自动进行去重
// 用一个map tableToFoods记录每桌所点的所有菜
// orders遍历完毕后，将foods和tables中的内容放入一个List，并排序
// 最后按照table号从小到大的顺序遍历，从tableToFoods中获取当前table所点的所有菜，并添加到结果中

class Solution {
    public List<List<String>> displayTable(List<List<String>> orders) {
        Set<String> foods = new HashSet<>();
        Set<Integer> tables = new HashSet<>();
        Map<Integer, List<String>> tableToFoods = new HashMap<>();

        for (List<String> order : orders) {
            String table = order.get(1);
            int tableInt = Integer.parseInt(table);
            String food = order.get(2);
            tables.add(tableInt);
            foods.add(food);
            List<String> foodsOfTable = tableToFoods.getOrDefault(tableInt, new ArrayList<>());
            foodsOfTable.add(food);
            tableToFoods.put(tableInt, foodsOfTable);
        }

        // sort foods
        List<String> foodList = new ArrayList<>(foods);
        Collections.sort(foodList);

        // sort tables
        List<Integer> tableList = new ArrayList<>(tables);
        Collections.sort(tableList);

        // build the result
        List<List<String>> result = new ArrayList<>();
        // first row
        List<String> firstRow = new ArrayList<>();
        firstRow.add("Table");
        firstRow.addAll(foodList);
        result.add(firstRow);

        for (Integer table : tableList) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(table));
            // build foodCount for current table
            Map<String, Integer> foodCount = new HashMap<>();
            List<String> foodsOfTable = tableToFoods.get(table);
            for (String food : foodsOfTable) {
                int count = foodCount.getOrDefault(food, 0);
                foodCount.put(food, count + 1);
            }
            for (String food : foodList) {
                int count = foodCount.getOrDefault(food, 0);
                String countStr = String.valueOf(count);
                row.add(countStr);
            }
            result.add(row);
        }

        return result;
    }
}