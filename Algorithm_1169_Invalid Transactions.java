import java.util.*;

// 方法：定义内部类Transaction。然后遍历transactions
// 对于每个name，将其放入一个list，name到list的映射用map来存储
// 然后使用brute-force直接对每个name下的list中所有transaction进行二维的遍历，判断transaction的amount和时间差以及地理位置信息是否满足要求
// 如果不满足要求，就把对应下标存入一个set
// list遍历完后，将set中所有下标对应的transaction加入到结果result中

class Solution {

    class Transaction {
        public String name;
        public int time;
        public int amount;
        public String city;
        public Transaction(String name, int time, int amount, String city) {
            this.name = name;
            this.time = time;
            this.amount = amount;
            this.city = city;
        }

        @Override
        public String toString() {
            return name + "," + time + "," + amount + "," + city;
        }
    }

    public List<String> invalidTransactions(String[] transactions) {
        List<String> result = new ArrayList<>();
        if (transactions == null || transactions.length == 0) {
            return result;
        }
        Map<String, List<Transaction>> map = new HashMap<>();
        for (String s : transactions) {
            Transaction transaction = parseTransaction(s);
            List<Transaction> transactionsOfName = map.getOrDefault(transaction.name, new ArrayList<>());
            transactionsOfName.add(transaction);
            map.put(transaction.name, transactionsOfName);
        }

        // for each person, check his or her transactions
        for (Map.Entry<String, List<Transaction>> e : map.entrySet()) {
            List<Transaction> transactionsOfName = e.getValue();
            Set<Integer> invalidIndexes = new HashSet<>();
            // check amount and city within 60min for this name with brute force
            for (int i = 0; i < transactionsOfName.size(); i++) {
                Transaction t1 = transactionsOfName.get(i);
                if (t1.amount > 1000) {
                    invalidIndexes.add(i);
                }
                for (int j = i + 1; j < transactionsOfName.size(); j++) {
                    Transaction t2 = transactionsOfName.get(j);
                    if (Math.abs(t1.time - t2.time) <= 60 && !t1.city.equals(t2.city)) {
                        invalidIndexes.add(i);
                        invalidIndexes.add(j);
                    }
                }
            }
            for (Integer idx : invalidIndexes) {
                result.add(transactionsOfName.get(idx).toString());
            }
        }

        return result;
    }

    private Transaction parseTransaction(String s) {
        String[] info = s.split(",");
        String name = info[0];
        int time = Integer.parseInt(info[1]);
        int amount = Integer.parseInt(info[2]);
        String city = info[3];
        return new Transaction(name, time, amount, city);
    }
}