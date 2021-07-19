import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

// 方法1：类似于LC_263。为primes中的每个数维护一个指针下标，放在数组pointers中
// 指针下标指向的是results中的一个元素
// 循环体内，每次都找到最小的一个下标i，使得results.get(pointers[i]) * primes[i]最小
// 则这个值就是下一个super ugly number，将其添加到results中
// 然后再遍历一次primes，把所有使得results.get(pointers[i]) * primes[i] == minValue的pointers[i]都++
// 这一步是为了去重

class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        if (n == 1) {
            return 1;
        }

        int[] pointers = new int[primes.length];
        List<Integer> results = new ArrayList<>();
        results.add(1);

        while (results.size() < n) {
            // look for the pointer that needs to be advanced
            int minIndex = 0;
            int minValue = results.get(pointers[0]) * primes[0];
            for (int i = 1; i < primes.length; i++) {
                if (results.get(pointers[i]) * primes[i] < minValue) {
                    minIndex = i;
                    minValue = results.get(pointers[i]) * primes[i];
                }
            }

            results.add(minValue);

            // de-duplicate, add the pointers for every i where results.get(pointers[i]) * primes[i] == minValue
            for (int i = 0; i < primes.length; i++) {
                if (minValue == results.get(pointers[i]) * primes[i]) {
                    pointers[i]++;
                }
            }
        }

        return results.get(n - 1);
    }
}

// 方法2：堆排序

class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        Set<Long> visited = new HashSet<>(); // for de-duplicating
        long result = 0;
        pq.offer(1L);
        while (n > 0) {
            result = pq.poll();
            for (int prime : primes) {
                long uglyNumber = result * prime;
                if (!visited.contains(uglyNumber)) {
                    pq.offer(uglyNumber);
                    visited.add(uglyNumber);
                }
            }
            n--;
        }
        return (int) result;
    }
}