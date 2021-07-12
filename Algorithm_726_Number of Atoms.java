import java.util.Stack;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

// 方法：栈+哈希表
// 对于括号类型的问题，通用的解法是递归或栈
// 每一对括号之间的内容视为一层
// 每一层都有其对应的一个map放在栈stack中，用于存放这一层的各个原子出现的次数
// 从左到右遍历formula，有以下三种情况：
// 1. 当前字符是左括号（，向stack中压入一个新的map，用于存放这新的一层的原子的计数
// 2. 当前字符是右括号），从stack中pop出这一层的原子的计数atomCountMap，并解析右括号之后可能存在的数字coefficient
//        将atomCountMap中所有原子的个数都乘以coefficient，然后再取栈顶的outerAtomCountMap，它对应于外层括号的原子计数
//        把内层的原子计数atomCountMap中的所有元素都加到外层的outerAtomCountMap上。如果outerAtomCountMap不存在，说明当前已经是
//        最外层，直接把atomCountMap再push回stack中
// 3. 当前字符是大写字母，表明是一个原子的开始。则继续向右移动变量i，获得原子的名称和对应的计数，并加到栈顶的map中

class Solution {
    public String countOfAtoms(String formula) {
        // add a pair of parentheses out of formula to make it easy for handling
        formula = "(" + formula + ")1";
        Stack<Map<String, Integer>> stack = new Stack<>();

        char[] formulaCharArr = formula.toCharArray();
        int i = 0;
        while (i < formulaCharArr.length) {
            if (formulaCharArr[i] == '(') { // push a new map into stack to calculate the atom count between the parentheses
                stack.push(new HashMap<>());
                i++;
            } else if (formulaCharArr[i] == ')') { // pop the map from the stack, and mutiply all the atom counts in the map by the number following the right parenthesis
                int coefficient = 0;
                i++;
                while (i < formulaCharArr.length && Character.isDigit(formulaCharArr[i])) {
                    coefficient = coefficient * 10 + (formulaCharArr[i] - '0');
                    i++;
                }
                if (coefficient == 0) { // no digits following the )
                    coefficient = 1;
                }
                Map<String, Integer> atomCountMap = stack.pop();
                for (Map.Entry<String, Integer> entry : atomCountMap.entrySet()) {
                    entry.setValue(entry.getValue() * coefficient);
                }

                // add the atom counts between the inner parentheses to the outer one
                if (!stack.isEmpty()) {
                    Map<String, Integer> outerAtomCountMap = stack.pop();
                    for (Map.Entry<String, Integer> entry : atomCountMap.entrySet()) {
                        String atom = entry.getKey();
                        int innerCount = entry.getValue();
                        int outerCount = outerAtomCountMap.getOrDefault(atom, 0);
                        outerCount += innerCount;
                        outerAtomCountMap.put(atom, outerCount);
                    }
                    stack.push(outerAtomCountMap);
                } else { // atoMCountMap is the map for the whole formula, push it back
                    stack.push(atomCountMap);
                }
            } else if (Character.isUpperCase(formulaCharArr[i])) { // a new atom
                // get the atom's name
                String atom = "" + formulaCharArr[i];
                i++;
                while (i < formulaCharArr.length && Character.isLowerCase(formulaCharArr[i])) {
                    atom += formulaCharArr[i];
                    i++;
                }
                // get the atom's count
                int atomCount = 0;
                if (Character.isDigit(formulaCharArr[i])) { // count of the current atom
                    while (i < formulaCharArr.length && Character.isDigit(formulaCharArr[i])) {
                        atomCount = atomCount * 10 + (formulaCharArr[i] - '0');
                        i++;
                    }
                } else { // another atom or (, count of the current atom is 1
                    atomCount = 1;
                }
                // add the atom's count to map
                Map<String, Integer> curAtomCountMap = stack.peek();
                int atomCountInMap = curAtomCountMap.getOrDefault(atom, 0);
                curAtomCountMap.put(atom, atomCountInMap + atomCount);
            }
        }

        // the only one map in the stack now is the atom count for the whole formula
        Map<String, Integer> atomCountMap = stack.pop();
        List<String> atoms = new ArrayList<>();
        // sort atoms according to lexicographical order
        atoms.addAll(atomCountMap.keySet());
        Collections.sort(atoms);
        StringBuilder stringBuilder = new StringBuilder();
        for (String atom : atoms) {
            int count = atomCountMap.get(atom);
            stringBuilder.append(atom);
            if (count > 1) {
                stringBuilder.append(count);
            }
        }
        return stringBuilder.toString();
    }
}